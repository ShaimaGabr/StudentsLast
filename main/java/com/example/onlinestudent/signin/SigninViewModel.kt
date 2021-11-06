package com.example.onlinestudent.signin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SigninViewModel: ViewModel(){
    var username= MutableLiveData<String>("")
    var userid= MutableLiveData<String>("")
    var selectedItemPosition= MutableLiveData<String>("")

    fun getdata(context: Context){
        // Toast.makeText(context,selectedItemPosition.value,Toast.LENGTH_SHORT).show()
//        ConnnectionSignin.Retrofit_connection().signin(username.value!!,userid.value!!).enqueue(object :Callback<ModelSignin>{
//            override fun onResponse(call: Call<ModelSignin>, response: Response<ModelSignin>) {
//                Navigation.findNavController(view).navigate(R.id.action_home2_to_welcome)
//            }
//
//            override fun onFailure(call: Call<ModelSignin>, t: Throwable) {
//               Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
//            }
//        })
        CoroutineScope(Dispatchers.IO).async {
            //Toast.makeText(context,username.value!!,Toast.LENGTH_SHORT).show()
            val response =ConnnectionSignin.Retrofit_connection().signin(username.value!!,userid.value!!)
            CoroutineScope(Dispatchers.Main).async {
                Log.i("fir","Clicked")
                if(response.isSuccessful){
                  //  Toast.makeText(context,response.body()!![0].app_id, Toast.LENGTH_SHORT).show()

                    context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
                        .edit()
                        .putString("NAME", username.value)
                        .putString("ID", userid.value)
                        .putString("STUDENT_ID",response.body()!![0].app_id)
                        .putString("COURSE_ID",response.body()!![0].course_id)
                        .putString("STAT",selectedItemPosition.value)
                        .apply()
                    val intent= Intent(context,MainActivity::class.java)

                    context. startActivity(intent)


                }else{
                    Toast.makeText(context,"failed${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}