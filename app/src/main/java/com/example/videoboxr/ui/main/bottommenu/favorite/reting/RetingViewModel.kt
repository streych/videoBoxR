package com.example.videoboxr.ui.main.bottommenu.favorite.reting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoboxr.model.AppState
import com.example.videoboxr.model.repository.Repository
import com.example.videoboxr.model.repository.RepositoryImpl

class RetingViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        val rand = (0..1).random()
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            /*if(rand == 0){
                liveDataToObserve.postValue(
                    AppState.Success(repository.getMovieFromLocal()))
            }
            else{
                liveDataToObserve.postValue(AppState.Error())
            }*/
            liveDataToObserve.postValue(
                AppState.Success(repository.getMovieFromLocal()))
        }.start()
    }
}