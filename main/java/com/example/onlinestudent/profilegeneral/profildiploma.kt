package com.example.onlinestudent.profilegeneral

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentProfildiplomaBinding
import com.example.onlinestudent.signin.MainActivity


class profildiploma : Fragment() {

    lateinit var binding:FragmentProfildiplomaBinding
    val viewmodel:ViewModelProfile by viewModels()
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
        binding=FragmentProfildiplomaBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.information(requireContext())
//        viewmodel.diplomaatte()
//        viewmodel.attend.observe(viewLifecycleOwner, Observer {
//            binding.attend.adapter=AdapterAttDip(it,viewmodel)
//            binding.attend.adapter!!.notifyDataSetChanged()
//
//        })

        /////////////////////////////////////

//          if(viewmodel.img.value==null){
//            //  binding.cardView2.visibility=View.VISIBLE
//              viewmodel.img.value="1"
        viewmodel.name(viewmodel.course_id.value!!)
        viewmodel.newsListo.observe(viewLifecycleOwner, Observer {
            binding.attend.adapter=coursesAdapter(it,viewmodel,requireContext(),requireView())
        })
//          }else{
//             // binding.cardView2.visibility=View.INVISIBLE
//              viewmodel.img.value=null
//          }

    }

}