package com.example.videoboxr.model

import com.example.videoboxr.model.data.Movie

sealed class AppState{
    data class Success(val movieData: List<Movie>): AppState()
    class Error(): AppState()
    object Loading: AppState()
}
