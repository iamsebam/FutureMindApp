package com.sebastianmatyjaszczyk.mainfeature.presentation.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sebastianmatyjaszczyk.mainfeature.R
import com.sebastianmatyjaszczyk.mainfeature.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null

    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyViewBinding(view)
        setupWebViewClient()
        setupSwipeRefreshListener()
        loadUrl(safeArgs.detailUrl)
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
        binding.swipeRefresh.isRefreshing = loading
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
    }
}
