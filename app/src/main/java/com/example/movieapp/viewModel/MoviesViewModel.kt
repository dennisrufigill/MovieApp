package com.example.movieapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.api.ApiService
import com.example.movieapp.paging.AnimationPagingSource
import com.example.movieapp.paging.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class MoviesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val moviesListData = Pager(PagingConfig(pageSize = 1)) {
        AnimationPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)


    val popularMoviesList = Pager(PagingConfig(pageSize = 1)) {
        MoviesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}