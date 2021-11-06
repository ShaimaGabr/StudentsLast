package com.example.onlinestudent.scanegeneral

import android.content.Intent
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
import com.example.onlinestudent.databinding.FragmentRecorddatabaseBinding

class Recorddatabase : Fragment() {
    lateinit var binding: FragmentRecorddatabaseBinding
    val viewmodedl:LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val callback1=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.action_recorddatabase_to_scan)

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback1)
        // Inflate the layout for this fragment
        binding= FragmentRecorddatabaseBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodedl
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val pref= requireContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
//        val stat=pref.getString("STAT", null)
//        val coid=pref.getString("COURSE_ID", null)
//        val stuid=pref.getString("STUDENT_ID", null)
        // Toast.makeText(requireContext(),viewmodedl.id,Toast.LENGTH_SHORT).show()
        viewmodedl.insert(requireContext())
        viewmodedl.attend.observe(viewLifecycleOwner, Observer {
            binding.cours.adapter=cocoAdapter(it,viewmodedl,requireContext())
            binding.cours.adapter!!.notifyDataSetChanged()

        })
    }


}