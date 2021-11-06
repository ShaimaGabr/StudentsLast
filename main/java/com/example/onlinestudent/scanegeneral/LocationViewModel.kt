package com.example.onlinestudent.scanegeneral

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestudent.ConnectionAttendance
import com.example.onlinestudent.profilegeneral.course.ModelnameCourseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.*

class LocationViewModel:ViewModel() {
    var inseert=MutableLiveData<String>("")
    var attend =MutableLiveData<List<ModelnameCourseItem>>()
    val img=MutableLiveData<String>(null)
    var GpsStatus = false
    ///////////////////////////////////////

    @SuppressLint("NewApi", "NullSafeMutableLiveData")
    fun insert(context: Context){
        val date= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val time=SimpleDateFormat("h:m", Locale.getDefault()).format(Date())
        val pref= context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val stat=pref.getString("STAT", null)
        val coid=pref.getString("COURSE_ID", null)
        val stuid=pref.getString("STUDENT_ID", null)
        val datago=pref.getString("GOIN", "OFF")
        val idtga=pref.getString("ID", null)
        if(stat=="COURSE"){
           // img.value="1"
            CoroutineScope(Dispatchers.IO).async {
                val response            = ConnectionAttendance.Retrofit_connection().ccnam(idtga!!
                )
                CoroutineScope(Dispatchers.Main).async {
                    if(response.isSuccessful){
                       // newsList.value=response.body()!!.articles
                        attend.value=response.body()!!
                    }else{
                        Toast.makeText(context,"failed${response.message()}",Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }else{
            CoroutineScope(Dispatchers.IO).async {
                val response            =ConnectionAttendance.Retrofit_connection().scandiploma(stuid!!,date,datago!!,time)
                CoroutineScope(Dispatchers.Main).async {
                    if(response.isSuccessful){
                        // newsList.value=response.body()!!.articles
                        inseert.value=response.body()!!.response
                    }else{
                        Toast.makeText(context,"failed${response.message()}",Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }

    }
@RequiresApi(Build.VERSION_CODES.N)
fun scanco(appid:String,coursid:String,context: Context){
    val date= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    val time=SimpleDateFormat("h:m", Locale.getDefault()).format(Date())
    val pref= context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
    val datago=pref.getString("GOIN", "OFF")
    CoroutineScope(Dispatchers.IO).async {
        val response            =ConnectionAttendance.Retrofit_connection().scanCourse(appid,date,
                coursid,datago!!,time
        )
        CoroutineScope(Dispatchers.Main).async {
            if(response.isSuccessful){
                // newsList.value=response.body()!!.articles
                inseert.value=response.body()!!.response
            }else{
                Toast.makeText(context,"failed${response.message()}",Toast.LENGTH_SHORT).show()
            }

        }
    }
}


}