package com.example.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.databinding.AnimationItemBinding
import com.example.movieapp.model.RickMorty

class AnimationPagerAdapter : PagingDataAdapter<RickMorty, AnimationPagerAdapter.MoviesViewHolder>(diffCallback) {

    inner class MoviesViewHolder(val binding : AnimationItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>(){
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       val currentItem = getItem(position) //get Item is from Paging Adapter

        holder.binding.apply {
            moviesTitle.text = currentItem?.name
            movieImage.load(currentItem?.image){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
      return MoviesViewHolder(
          AnimationItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }


}