package com.example.onlinestudent.profilegeneral.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentContentcourseBinding
import com.example.onlinestudent.profilegeneral.ViewModelProfile


class Contentcourse : Fragment() {
    lateinit var binding: FragmentContentcourseBinding
    val viewmodel:ViewModelProfile by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val callback1=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.action_contentcourse_to_profilecourse)
                //////////////////////////////
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback1)
        // Inflate the layout for this fragment
        binding= FragmentContentcourseBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ///////////////
        // Toast.makeText(requireContext(),arguments?.getString("amount"),Toast.LENGTH_SHORT).show()
        binding.name.text=arguments?.getString("name")
        viewmodel.getdatafromserver(requireContext(),arguments?.getString("amount")!!,viewmodel.coursidapp.value!!)
        // viewmodel.contentcorse(requireContext())
        viewmodel.newsList.observe(viewLifecycleOwner, Observer {
            binding.attend.adapter=attendanceAdapter(it,viewmodel)
//            binding.attend.adapter!!.notifyDataSetChanged()
        })

    }



}