package com.example.assignment02

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.assignment02.databinding.ActivityDetailsBinding
import com.example.assignment02.viewmodel.MovieViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: MovieViewModel by viewModels()
    private val apiKey = "17cff8e7" // Replace with your real key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imdbId = intent.getStringExtra("movie_id") ?: return
        viewModel.fetchMovieDetails(imdbId, apiKey)

        viewModel.movieDetails.observe(this) { movie ->
            binding.tvDetailsTitle.text = movie.Title
            binding.tvDetailsYear.text = "${movie.Year} • ${movie.Runtime} • ${movie.Genre}\n\nIMDb Rating: ${movie.imdbRating}\n\n${movie.Plot}"

            Glide.with(this)
                .load(movie.Poster)
                .into(binding.imgDetailsPoster)
        }

        binding.btnGoBack.setOnClickListener {
            finish()
        }
    }
}
