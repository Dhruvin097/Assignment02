package com.example.assignment02.data.repository

import com.example.assignment02.data.remote.RetrofitInstance
import com.example.assignment02.data.model.MovieSearchResponse
import com.example.assignment02.data.model.MovieDetails
import retrofit2.Response

class MovieRepository {
    suspend fun searchMovies(query: String, apiKey: String): Response<MovieSearchResponse> {
        return RetrofitInstance.api.searchMovies(query, apiKey)
    }

    suspend fun getMovieDetails(imdbId: String, apiKey: String): Response<MovieDetails> {
        return RetrofitInstance.api.getMovieDetails(imdbId, apiKey)
    }
}
