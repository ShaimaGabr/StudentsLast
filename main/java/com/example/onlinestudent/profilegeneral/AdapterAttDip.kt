package com.example.onlinestudent.profilegeneral

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestudent.databinding.ShowoneattendBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class AdapterAttDip(private val dataset:List<DiplomaAttendItem>,val viewModel: ViewModelProfile) :
    RecyclerView.Adapter<ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val inflater = LayoutInflater.from(parent.context)

        val bindin = ShowoneattendBinding.inflate(inflater, parent, false)

        return ViewHold(bindin)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val movie = dataset[position]
        // viewModel.name(movie.course_id)
        //viewModel.vis.value=movie.course_id


        holder.bindin.text2.text=movie.date
        holder.bindin.text3.text=movie.goin
        holder.bindin.text1.text=movie.goout

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
class ViewHold(val bindin: ShowoneattendBinding) : RecyclerView.ViewHolder(bindin.root) {

}