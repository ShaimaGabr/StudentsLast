package com.example.onlinestudent.profilegeneral.course

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.onlinestudent.profilegeneral.ViewModelProfile
import com.example.onlinestudent.databinding.FragmentProfilecourseBinding
import com.example.onlinestudent.signin.MainActivity


class profilecourse : Fragment() {

    lateinit var binding:FragmentProfilecourseBinding
    val viewmodel: ViewModelProfile by viewModels()

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
        binding= FragmentProfilecourseBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.vewModel=viewmodel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.information(requireContext())
        // viewmodel.getdatafromserver(requireContext())
        viewmodel.contentcorse(requireContext())
        viewmodel.atten.observe(viewLifecycleOwner, Observer {
            binding.attend.adapter= CcourAdapter(it,viewmodel,requireContext(),requireView())
//            binding.attend.adapter!!.notifyDataSetChanged()
        })



    }


}