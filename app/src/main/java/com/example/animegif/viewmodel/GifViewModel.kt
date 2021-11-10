package com.example.animegif.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animegif.model.GifInfo
import com.example.animegif.model.GifModel
import com.example.animegif.repo.GifRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifViewModel: ViewModel() {

    private val _gifObj = MutableLiveData<List<GifInfo>>()
    val gifObj: MutableLiveData<List<GifInfo>> get() = _gifObj

    fun fetchData(path: String, amount:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GifRepo.getGifAPI(path, amount)
            val fetchedData = if (response.isSuccessful && response.body() != null)
                response.body()
            else GifModel(listOf<GifInfo>(GifInfo("https://nekos.best/api/v1/baka/001.gif",
            "Failed to fetch")))
            fetchedData.let{ _gifObj.postValue(it?.url)}
        }
    }

}