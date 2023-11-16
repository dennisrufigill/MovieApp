package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.FragmentAnimationBinding
import com.example.movieapp.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimationFragment :
    BaseFragment<FragmentAnimationBinding>(FragmentAnimationBinding::inflate) {

    private lateinit var moviesAdapter: AnimationPagerAdapter
    private val moviesViewModel: MoviesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadMoviesData()
    }

    private fun setupRecyclerView() {
        moviesAdapter = AnimationPagerAdapter()
        binding.moviesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = moviesAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }
    }

    private fun loadMoviesData() {
        lifecycleScope.launch {
            moviesViewModel.moviesListData.collect {
                moviesAdapter.submitData(it)
            }
        }
    }

}