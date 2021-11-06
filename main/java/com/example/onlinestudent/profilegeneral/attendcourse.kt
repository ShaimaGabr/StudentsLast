package com.example.onlinestudent.profilegeneral

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentAttendcourseBinding


class attendcourse : Fragment() {

    lateinit var binding: FragmentAttendcourseBinding
    val viewmodel:ViewModelProfile by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val callback1=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.action_attendcourse_to_profildiploma2)
                //////////////////////////////
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback1)
        // Inflate the layout for this fragment
        binding= FragmentAttendcourseBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref= requireContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val stat=pref.getString("STAT", null)
        val coid=pref.getString("COURSE_ID", null)
        val stuid=pref.getString("STUDENT_ID", null)
        binding.name.text=arguments?.getString("name")
       // Toast.makeText(requireContext(),arguments?.getString("amount")!!+":"+stuid, Toast.LENGTH_SHORT).show()
        viewmodel.selectattcourse(arguments?.getString("amount")!!,requireContext(),stuid!!)
        viewmodel.attend.observe(viewLifecycleOwner, Observer {
            binding.attend.adapter = AdapterAttDip(it, viewmodel)
        })
    }


}