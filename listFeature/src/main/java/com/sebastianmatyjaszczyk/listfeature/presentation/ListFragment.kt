package com.sebastianmatyjaszczyk.listfeature.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sebastianmatyjaszczyk.listfeature.R
import com.sebastianmatyjaszczyk.listfeature.databinding.ListFragmentBinding
import com.sebastianmatyjaszczyk.listfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewState
import com.sebastianmatyjaszczyk.listfeature.model.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()

    private val adapter by lazy { ListViewAdapter(onItemSelectedListener) }

    private lateinit var onItemSelectedListener: (item: ListItemViewEntity) -> Unit

    private var _binding: ListFragmentBinding? = null

    private val binding
        get() = _binding!! // not proud of that, but haven't used view binding yet in production and didn't want to waste too much time

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyViewBinding(view)
        setupOnItemSelectedListener()
        setupOnRefreshListener()
        setupListAdapter()
        observeViewState()
    }

    override fun onDestroyView() {
        removeViewBinding()
        super.onDestroyView()
    }

    private fun applyViewBinding(view: View) {
        _binding = ListFragmentBinding.bind(view)
    }

    private fun removeViewBinding() {
        _binding = null
    }

    private fun setupOnItemSelectedListener() {
        onItemSelectedListener = viewModel::onItemSelected
    }

    private fun setupListAdapter() {
        binding.listView.adapter = adapter
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, { handleViewState(it) })
    }

    private fun setupOnRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        viewModel.refreshData()
    }

    private fun handleViewState(viewState: ViewState) {
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

private fun View.setVisible(visible: Boolean, hiddenState: Int = View.GONE) {
    visibility = if (visible) View.VISIBLE else hiddenState
}
