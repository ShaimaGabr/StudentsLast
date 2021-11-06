package com.example.onlinestudent.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentFacebookBinding
import com.example.onlinestudent.signin.MainActivity


class facebook : Fragment() {

    lateinit var binding:FragmentFacebookBinding
    val viewmodel :HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val callback1=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                val intent= Intent(requireContext(), MainActivity::class.java)

                startActivity(intent)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback1)
        // Inflate the layout for this fragment
        binding= FragmentFacebookBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodel
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gros = 0.0F

        binding.webView.settings.javaScriptEnabled=true
        binding.webView.settings.setSupportZoom(false)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, Url: String?): Boolean {
                view?.loadUrl(Url!!)
                return true
            }
        }
        // binding.webView.loadUrl("https://www.facebook.com/ITGateAcademy/")
    }


}