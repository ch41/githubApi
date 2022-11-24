package com.example.github.ui.fragments.webviewfragment

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.github.databinding.FragmentWebViewBinding
import com.example.github.ui.MainActivity


class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    private val args: WebViewFragmentArgs by navArgs()

    companion object {
        const val MAX_PROGRESS = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater)
        initWebView()
        setWebClient()
        loadUrl(args.url)
        binding.webView.canGoBack()
        binding.webView.setOnKeyListener { _, keyCode, event ->
           if (event.action != KeyEvent.ACTION_DOWN)
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    requireActivity().onBackPressed()
                }
            }
            true
        }

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    private fun setWebClient() {
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                binding.progressBar.apply {
                    progress = newProgress
                    visibility =
                        if (newProgress < MAX_PROGRESS) ProgressBar.VISIBLE else ProgressBar.GONE
                }
            }
        }
    }

    private fun loadUrl(pageUrl: String) {
        binding.webView.loadUrl(pageUrl)
    }
}