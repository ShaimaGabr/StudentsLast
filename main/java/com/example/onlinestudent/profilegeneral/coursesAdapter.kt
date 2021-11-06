package com.example.onlinestudent.profilegeneral

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestudent.R
import com.example.onlinestudent.databinding.CorseitemBinding



class coursesAdapter ( private val movies: List<ModelnameItem>,val viewModel:ViewModelProfile,val context: Context,val view:View):
        RecyclerView.Adapter<MainViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CorseitemBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.nam.text=movie.coursename
        val bundle = bundleOf("amount" to movie.id,"name" to movie.coursename)
        holder.binding.linear.setOnClickListener {

           // Toast.makeText(context,movie.id,Toast.LENGTH_SHORT).show()

            //viewModel. selectattcourse(movie.id,context)
            Navigation.findNavController(view).navigate(R.id.action_profildiploma_to_attendcourse2,bundle)
          //  Navigation.findNavController(view).navigate(R.id.action_profildiploma_to_diplomaattendcorse,bundle)
        }


    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: CorseitemBinding) : RecyclerView.ViewHolder(binding.root) {

}