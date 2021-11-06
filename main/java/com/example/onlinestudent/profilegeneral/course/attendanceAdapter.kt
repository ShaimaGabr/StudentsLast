package com.example.onlinestudent.profilegeneral.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestudent.databinding.ShowoneattendBinding
import com.example.onlinestudent.profilegeneral.ViewModelProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class attendanceAdapter(private val dataSet: List<AttendanceModelItem>,val viewModel: ViewModelProfile) :
        RecyclerView.Adapter<VieHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        val inflater = LayoutInflater.from(parent.context)

        val bindin = ShowoneattendBinding.inflate(inflater, parent, false)

        return VieHolder(bindin)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: VieHolder, position: Int) {
        val movie = dataSet[position]
       // viewModel.name(movie.course_id)
        viewModel.vis.value=movie.course_id
//        CoroutineScope(Dispatchers.IO).async {
//            val response            =ConnectionAttendance.Retrofit_connection().coursenamec(movie.course_id)
//            CoroutineScope(Dispatchers.Main).async {
//                if(response.isSuccessful){
//                    holder.bindin.text1.text= response.body()!![0].Name
//                }else{
//
//                }
//
//
//            }}
        holder.bindin.text3.text=movie.goin
        holder.bindin.text2.text=movie.date
        holder.bindin.text1.text=movie.goout

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

}
class VieHolder(val bindin: ShowoneattendBinding) : RecyclerView.ViewHolder(bindin.root) {

}