package com.example.videoboxr.ui.main.bottommenu.favorite.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoboxr.model.AppState
import com.example.videoboxr.model.repository.Repository
import com.example.videoboxr.model.repository.RepositoryImpl

class MainViewModel(private val repository: Repository = RepositoryImpl())
    : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var THREAD_MILLIS = 1000

    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        Thread {
            Thread.sleep(THREAD_MILLIS.toLong())
                liveDataToObserve.postValue(
                    AppState.Success(repository.getMovieFromLocal()))
        }.start()
    }
}