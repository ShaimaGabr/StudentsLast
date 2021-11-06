package com.example.onlinestudent.scanegeneral

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestudent.databinding.CoItemBinding
import com.example.onlinestudent.profilegeneral.course.ModelnameCourseItem

class cocoAdapter (private val movies:List<ModelnameCourseItem>, val viewModel:LocationViewModel, val context: Context):
        RecyclerView.Adapter<MainViewder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CoItemBinding.inflate(inflater, parent, false)

        return MainViewder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MainViewder, position: Int) {
        val movie = movies[position]

        holder.binding.name.text=movie.coursename
        holder.binding.liner.setOnClickListener {

            //Toast.makeText(context,movie.id,Toast.LENGTH_SHORT).show()
           // viewModel.img.value=null
            viewModel.scanco(movie.app_id,movie.course_id,context)
            if(viewModel.img.value==null){
                //  binding.cardView2.visibility=View.VISIBLE
                viewModel.img.value="1"
        }else{
                viewModel.img.value=null
        }
        }


    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewder(val binding: CoItemBinding) : RecyclerView.ViewHolder(binding.root) {

}