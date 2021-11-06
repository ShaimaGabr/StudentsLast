package com.example.onlinestudent.home

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.onlinestudent.R

class HomeViewModel: ViewModel() {

    fun attend(view: View){
        Navigation.findNavController(view).navigate(R.id.action_home2_to_scan)
    }
    fun profil(view: View, context: Context){
        val pref= context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val stat=pref.getString("STAT", null)
        if(stat=="COURSE"){
            // Toast.makeText(context,stat,Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_home2_to_profilecourse)
        }
        else{
            Navigation.findNavController(view).navigate(R.id.action_home2_to_profildiploma)
        }
    }
    fun face(view: View){
        Navigation.findNavController(view).navigate(R.id.action_home2_to_facebook)
    }
    fun wep(view: View){
        Navigation.findNavController(view).navigate(R.id.action_home2_to_wepsite)
    }
    val webViewUrl = MutableLiveData<String>("https://www.facebook.com/ITGateAcademy/")

    val webViewUrlTwo = MutableLiveData<String>("https://www.itgateacademy.com/index.php")
    fun notifcation(view: View){
        //Navigation.findNavController(view).navigate(R.id.action_home2_to_notification)
    }
}