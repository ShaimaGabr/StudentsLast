package com.example.onlinestudent.profilegeneral.course

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestudent.profilegeneral.ViewModelProfile
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.CoItemBinding


class CcourAdapter (private val movies:List<ModelnameCourseItem>, val viewModel: ViewModelProfile, val context: Context, val view:View):
    RecyclerView.Adapter<ManViewder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManViewder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CoItemBinding.inflate(inflater, parent, false)

        return ManViewder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ManViewder, position: Int) {
        val movie = movies[position]

        holder.binding.name.text=movie.coursename
        holder.binding.liner.setOnClickListener {
//            viewModel.coursnam.value=movie.coursename
//            viewModel.coursid.value=movie.course_id
//           viewModel.coursidapp.value=movie.st_id
            val bundle = bundleOf("amount" to movie.course_id,"name" to movie.coursename)
            Navigation.findNavController(view).navigate(R.id.action_profilecourse_to_contentcourse,bundle)
//
           // Toast.makeText(context,movie.course_id+":"+movie.st_id,Toast.LENGTH_SHORT).show()
            //Toast.makeText(context,movie.id,Toast.LENGTH_SHORT).show()
            // viewModel.img.value=null
//            viewModel.scanco(movie.app_id,movie.course_id,context)
//            viewModel.img.value="0"
        }


    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class ManViewder(val binding: CoItemBinding) : RecyclerView.ViewHolder(binding.root) {

}