package com.example.videoboxr.model.repository

import com.example.videoboxr.model.data.Movie

class RepositoryImpl: Repository {
    override fun getMovieFromLocal(): Movie {
        return Movie(
            "Камень",
            "Лучший фильм((=",
            "12.12.2020",
            5
        )
    }
}