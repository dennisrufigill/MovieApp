package com.example.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.databinding.MoviesItemBinding
import com.example.movieapp.model.movieResponse.Result
import com.example.movieapp.utils.Constants.MOVIES_IMAGES_BASE_URL

class MoviesPagerAdapter :
    PagingDataAdapter<Result, MoviesPagerAdapter.MoviesViewHolder>(diffCallback) {

    var onItemClick: ((Result) -> Unit)? = null

    inner class MoviesViewHolder(val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
                binding.apply {
                    moviesTitle.text = result.title
                    movieImage.load(MOVIES_IMAGES_BASE_URL + result.poster_path) {
                        crossfade(true)
                        crossfade(1000)
                    }

                    itemView.setOnClickListener {
                        onItemClick?.invoke(result)
                    }
                }
            }

        }



    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = getItem(position) //get Item is from Paging Adapter
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


}