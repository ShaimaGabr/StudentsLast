package com.example.onlinestudent.scanegeneral

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.onlinestudent.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ScanViewModel:ViewModel() {
    var inout=MutableLiveData<String>("")
    var outdata=MutableLiveData<String>("")
    var scan= MutableLiveData<String>("")
    fun setupPermision(activity: Activity,context: Context) {
        val Permission= ContextCompat.checkSelfPermission(context,android.Manifest.permission.CAMERA)
        if(Permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CAMERA), 101)
        }
    }
    ///////////////////////////////////////////////
    fun indata(context: Context){
        context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
            .edit()
            .putString("GOIN", inout.value)
            .apply()
    }
    fun outdta(context: Context){
        val pref= context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val data=pref.getString("GOIN", "OFF")
        outdata.value=data!!

    }

    fun move(view: View){
        if (scan.value==""){}
        else{
            Navigation.findNavController(view).navigate(R.id.action_scan_to_recorddatabase)
        }
    }
     fun codeScanner(codeScanner: CodeScanner, context: Context, activity: Activity, view:View) {
        // codeScanner= codeScanner(context, scan.value!!)
         codeScanner.apply {
             camera= CodeScanner.CAMERA_BACK
             formats= CodeScanner.ALL_FORMATS
             autoFocusMode= AutoFocusMode.SAFE
             scanMode= ScanMode.CONTINUOUS
             isAutoFocusEnabled=true
             isFlashEnabled=false
             decodeCallback= DecodeCallback {
                 activity?.runOnUiThread {
                     scan.value=it.text
                     move(view)
                     ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                     //  DesignerToast.Info(requireContext(), "RUNAWAY YOUR PHONE AND WAIT", Gravity.CENTER, Toast.LENGTH_SHORT)
                     // Toast.makeText(context,it.text,Toast.LENGTH_SHORT).show()
                    // move(view)
                     //  Navigation.findNavController(requireView()).navigate(R.id.action_attendance2_to_location)

                     // checkplace(it.text)
                 }

             }
             errorCallback= ErrorCallback {
                 activity?.runOnUiThread{
                     Log.e("Main","Camera initialization error:${it.message}")
                 }
             }
         }

    }

}