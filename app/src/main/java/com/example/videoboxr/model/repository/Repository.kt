package com.example.videoboxr.model.repository

import com.example.videoboxr.model.data.Movie

interface Repository {
    fun getMovieFromLocal(): Movie
}