package com.example.onlinestudent.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentHomeBinding


class home : Fragment() {
    lateinit var bindind:FragmentHomeBinding
    val viewmodel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                @SuppressLint("NewApi")
                override fun handleOnBackPressed() {
                    // in here you can do logic when backPress is clicked
                    // activity!!.onBackPressed()
                    // Toast.makeText(requireContext(),"Press back again to exit",Toast.LENGTH_SHORT).show()
                    val intent= Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    activity!!.finish()

                }
            }
        )
        // Inflate the layout for this fragment
        bindind= FragmentHomeBinding.inflate(inflater)
        bindind.viewModel=viewmodel
        bindind.lifecycleOwner=this
        return bindind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindind.attendance.setOnClickListener {
            viewmodel.attend(requireView())
        }
        bindind.profil.setOnClickListener {
            viewmodel.profil(requireView(),requireContext())
        }
        bindind.face.setOnClickListener {
            viewmodel.face(requireView())
        }
        bindind.wepsit.setOnClickListener {
            viewmodel.wep(requireView())
        }
        bindind.imageView3.setOnClickListener {
            viewmodel.notifcation(requireView())
        }
    }


}