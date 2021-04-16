package com.sebastianmatyjaszczyk.mainfeature.presentation.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sebastianmatyjaszczyk.mainfeature.R
import com.sebastianmatyjaszczyk.mainfeature.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null

    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyViewBinding(view)
        setupWebViewClient()
        setupSwipeRefreshListener()
        observeUrlChanges()
    }

    private fun observeUrlChanges() {
        viewModel.detailUrlLiveData.observe(viewLifecycleOwner) { url ->
            loadUrl(url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeViewBinding()
    }

    private fun applyViewBinding(view: View) {
        _binding = FragmentDetailBinding.bind(view)
    }

    private fun removeViewBinding() {
        _binding = null
    }

    private fun loadUrl(url: String) {
        displayLoading()
        binding.webView.loadUrl(url)
    }

    private fun displayLoading(loading: Boolean = true) {
        _binding?.swipeRefresh?.isRefreshing = loading
    }

    private fun setupSwipeRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            reloadWebView()
        }
    }

    private fun reloadWebView() {
        binding.webView.reload()
    }

    private fun setupWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                displayLoading(false)
            }
        }
        binding.webView.settings.javaScriptEnabled = true
    }
}
