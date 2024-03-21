package com.example.kotlin13

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.kotlin13.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences("WebViewPrefs", Context.MODE_PRIVATE)

        with(binding.webView) {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(sharedPreferences.getString("lastUrl", "https://www.google.com")!!)

            setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
                if (keyCode == android.view.KeyEvent.KEYCODE_BACK && canGoBack()) {
                    goBack()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    override fun onStop() {
        super.onStop()
        val url = binding.webView.url
        val editor = requireActivity().getSharedPreferences("WebViewPrefs", Context.MODE_PRIVATE).edit()
        editor.putString("lastUrl", url).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
