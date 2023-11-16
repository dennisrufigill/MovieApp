package com.example.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api.ApiService
import com.example.movieapp.model.movieResponse.Result
import com.example.movieapp.utils.Constants.MOVIES_API_KEY
import com.example.movieapp.utils.Constants.MOVIE_BASE_URL_WITH_POPULAR_MOVIES

class MoviesPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPopularMovies(
                url = MOVIE_BASE_URL_WITH_POPULAR_MOVIES,
                api_key = MOVIES_API_KEY,
                page = currentPage
            )
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<Result>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}