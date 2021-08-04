package com.example.videoboxr.model.repository

import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.model.data.getMovies

class RepositoryImpl: Repository {
    override fun getMovieFromLocal(): List<Movie> {
        return getMovies()
    }
}