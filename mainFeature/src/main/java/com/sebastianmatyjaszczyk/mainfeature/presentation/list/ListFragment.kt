package com.sebastianmatyjaszczyk.mainfeature.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sebastianmatyjaszczyk.commonlib.ViewState
import com.sebastianmatyjaszczyk.commonlib.setVisible
import com.sebastianmatyjaszczyk.mainfeature.R
import com.sebastianmatyjaszczyk.mainfeature.databinding.FragmentListBinding
import com.sebastianmatyjaszczyk.mainfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.mainfeature.domain.ViewEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModels<ListViewModel>()

    private val adapter by lazy { ListViewAdapter(onItemSelectedListener) }

    private lateinit var onItemSelectedListener: (item: ListItemViewEntity) -> Unit

    private var _binding: FragmentListBinding? = null

    private val binding
        get() = _binding!! // not proud of that, but haven't used view binding yet in production and didn't want to waste too much time

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyViewBinding(view)
        setupOnItemSelectedListener()
        setupOnRefreshListener()
        setupListAdapter()
        observeViewState()
        observeDetailViewNavigationAction()
    }

    override fun onDestroyView() {
        clearAdapter()
        removeViewBinding()
        super.onDestroyView()
    }

    private fun clearAdapter() {
        binding.listView.adapter = null
    }

    private fun applyViewBinding(view: View) {
        _binding = FragmentListBinding.bind(view)
    }

    private fun removeViewBinding() {
        _binding = null
    }

    private fun setupOnItemSelectedListener() {
        onItemSelectedListener = viewModel::onListItemSelected
    }

    private fun setupListAdapter() {
        binding.listView.adapter = adapter
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, { handleViewState(it) })
    }

    private fun observeDetailViewNavigationAction() {
        viewModel.detailViewNavigationAction.observe(viewLifecycleOwner) { handleDetailViewNavigationAction() }
    }

    private fun handleDetailViewNavigationAction() {
        if (!isMasterDetailView()) {
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }

    private fun isMasterDetailView() = binding.detailFragmentContainer != null

    private fun setupOnRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        viewModel.refreshData()
    }

    private fun handleViewState(viewState: ViewState<ViewEntity>) {
        when (viewState) {
            is ViewState.Loading -> displayLoading()
            is ViewState.Error -> displayError(viewState.message)
            is ViewState.Success -> displayData(viewState.data)
        }
    }

    private fun displayData(viewEntity: ViewEntity) {
        displayLoading(false)
        setErrorVisible(false)
        setListVisible(true)
        setListData(viewEntity)
    }

    private fun displayError(message: String) {
        displayLoading(false)
        setListVisible(false)
        setErrorVisible(true)
        setErrorText(message)
    }

    private fun displayLoading(isLoading: Boolean = true) {
        binding.swipeRefresh.isRefreshing = isLoading
    }

    private fun setListData(viewEntity: ViewEntity) {
        adapter.submitList(viewEntity.data)
    }

    private fun setErrorText(message: String) {
        binding.errorText.text = message
    }

    private fun setErrorVisible(visible: Boolean) {
        binding.errorText.setVisible(visible)
    }

    private fun setListVisible(visible: Boolean) {
        binding.listView.setVisible(visible)
    }
}
