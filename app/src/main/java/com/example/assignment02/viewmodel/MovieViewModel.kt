package com.example.assignment02.viewmodel

import androidx.lifecycle.*
import com.example.assignment02.data.model.Movie
import com.example.assignment02.data.model.MovieDetails
import com.example.assignment02.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun searchMovies(query: String, apiKey: String) {
        viewModelScope.launch {
            val response = repository.searchMovies(query, apiKey)
            if (response.isSuccessful) {
                _movies.postValue(response.body()?.Search ?: emptyList())
            }
        }
    }

    fun fetchMovieDetails(imdbId: String, apiKey: String) {
        viewModelScope.launch {
            val response = repository.getMovieDetails(imdbId, apiKey)
            if (response.isSuccessful) {
                _movieDetails.postValue(response.body())
            }
        }
    }
}
