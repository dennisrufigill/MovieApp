package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.model.movieResponse.Result
import com.example.movieapp.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private lateinit var moviesAdapter: MoviesPagerAdapter
    private val moviesViewModel: MoviesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadMoviesData()
    }


    private fun setupRecyclerView() {
        moviesAdapter = MoviesPagerAdapter()
        binding.moviesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = moviesAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }

        moviesAdapter.onItemClick = {
            val moviesBundle = Bundle()
            moviesBundle.putSerializable("Movies", it)
         //  findNavController().navigate(R.id.moviesDetailFragment, moviesBundle)
            findNavController().navigate(R.id.action_homeParentFragment_to_moviesDetailFragment2, moviesBundle)
        }
    }

    private fun loadMoviesData() {
        lifecycleScope.launch {
            moviesViewModel.popularMoviesList.collect {
                //moviesList = it
                moviesAdapter.submitData(it)
            }
        }
    }


}