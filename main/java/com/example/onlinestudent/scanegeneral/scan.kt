package com.example.onlinestudent.scanegeneral

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.budiyev.android.codescanner.CodeScanner
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.FragmentScanBinding
import com.example.onlinestudent.signin.MainActivity
import it.ngallazzi.fancyswitch.FancyState
import it.ngallazzi.fancyswitch.FancySwitch

private const val CAMERA_REQUEST_CODE=101
class scan : Fragment() {
    lateinit var binding: FragmentScanBinding
    val viewmodel:ScanViewModel by viewModels()
    lateinit var codeScanner: CodeScanner


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
        /////////////////////////////////
        // Inflate the layout for this fragment
        binding= FragmentScanBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewmodel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ///////////////////////////1//////////////
        //  viewmodel.scan.value= binding.scannerView.toString()
        viewmodel.outdta(requireContext())
        if(viewmodel.outdata.value=="ON"){
            binding.fancySwitch.setState(FancyState.State.ON)
            binding.goin.setTextColor(Color.parseColor("#20074C"))
        }
        else if(viewmodel.outdata.value=="OFF"){
            binding.fancySwitch.setState(FancyState.State.OFF)
            binding.goout.setTextColor(Color.parseColor("#20074C"))
        }
        else{}
        binding.fancySwitch.setSwitchStateChangedListener(object : FancySwitch.StateChangedListener{
            override fun onChanged(newState: FancyState) {
                if("OFF".equals(newState.id.toString())){
//                    Toast.makeText(requireContext(),
//                        "offfffffffffffff" , Toast.LENGTH_SHORT).show()
                    viewmodel.inout.value="OFF"
                    viewmodel.indata(requireContext())
                    binding.goout.setTextColor(Color.parseColor("#20074C"))
                    binding.goin.setTextColor(Color.parseColor("#8A8686"))
                }else{
                    viewmodel.inout.value="ON"
                    viewmodel.indata(requireContext())
                    binding.goin.setTextColor(Color.parseColor("#20074C"))
                    binding.goout.setTextColor(Color.parseColor("#8A8686"))
                }

            } })
        viewmodel.setupPermision(requireActivity(),requireContext())
        codeScanner=CodeScanner(requireContext(),binding.scannerView)
        viewmodel.codeScanner(codeScanner,requireContext(), Activity(),requireView())

        ///////////////////////
        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
        ///////////////////////////////2//////////////////////

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if(grantResults.isEmpty()||grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(),"you need the camera permission to be able to use this app", Toast.LENGTH_SHORT).show()
                }
                else{}
            }
        }
    }


}