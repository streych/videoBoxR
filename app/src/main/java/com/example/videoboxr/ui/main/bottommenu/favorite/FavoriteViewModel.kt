package com.example.videoboxr.ui.main.bottommenu.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoboxr.model.AppState
import com.example.videoboxr.model.repository.Repository
import com.example.videoboxr.model.repository.RepositoryImpl

class FavoriteViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    var THREAD_MILLIS = 1000

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