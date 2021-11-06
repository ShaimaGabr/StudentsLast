package com.example.onlinestudent.signin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var backPressedTime: Long = 0

    lateinit var nav: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        nav= Navigation.findNavController(this, R.id.nav_host_fragment)
       binding.bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_baseline_crop_free_24))
       binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_baseline_emoji_people_24))
       binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_baseline_home_24))
       binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_baseline_facebook_24))
       binding.bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.itgat))
       binding.bottomNavigation.show(2)
        binding.bottomNavigation.setOnClickMenuListener {
        when(it.id) {
            2 -> {
                nav.popBackStack(R.id.home2, false)
            }
            0 -> {
                nav.popBackStack(R.id.home2, false)
                nav.navigate(R.id.action_home2_to_scan)
            }
            3 -> {
                nav.popBackStack(R.id.home2, false)
                nav.navigate(R.id.action_home2_to_facebook)
            }
            4 -> {
                nav.popBackStack(R.id.home2, false)
                nav.navigate(R.id.action_home2_to_wepsite)
            }
            1 -> {
                nav.popBackStack(R.id.home2, false)
                // nav.navigate(R.id.action_welcome_to_profildiploma)
                val pref = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
                val stat = pref.getString("STAT", null)
                if (stat == "COURSE") {
                    // Toast.makeText(context,stat,Toast.LENGTH_SHORT).show()
                    nav.navigate(R.id.action_home2_to_profilecourse)
                } else {
                    nav.navigate(R.id.action_home2_to_profildiploma)
                }
            }
        }
        }}
    override fun onStart() {
        super.onStart()
        val pref= getSharedPreferences("PREFS_NAME", MODE_PRIVATE)
        val name=pref.getString("NAME", null)
        // Toast.makeText(context,"Welcome"+name,Toast.LENGTH_SHORT).show()
        if (name==null){
            val intent= Intent(this, Signin::class.java)
            startActivity(intent)
        }else{

        }
    }
}