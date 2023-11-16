package com.example.movieapp.ui

import android.os.Bundle
import android.view.View
import coil.load
import com.example.movieapp.databinding.FragmentMoviesDetailBinding
import com.example.movieapp.model.movieResponse.Result
import com.example.movieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment :
    BaseFragment<FragmentMoviesDetailBinding>(FragmentMoviesDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesData = arguments?.getSerializable("Movies") as Result
        binding.apply {
            movieImage.load(Constants.MOVIES_IMAGES_BASE_URL + moviesData.poster_path) {
                crossfade(true)
                crossfade(1000)
            }

            moviesData.apply {
                moviesTitle.text = title
                releaseDate.text = release_date
                originalLanguage.text = original_language
                tvPopularity.text = popularity.toString()
                tvDescription.text = overview
            }

        }
    }


}