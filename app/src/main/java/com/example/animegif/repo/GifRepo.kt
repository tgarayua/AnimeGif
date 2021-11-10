package com.example.animegif.repo

import com.example.animegif.model.GifModel
import com.example.animegif.repo.remote.RetrofitInstance
import retrofit2.Response

object GifRepo {
    suspend fun getGifAPI(path: String, amount: Int): Response<GifModel> {
        return RetrofitInstance.gifService.getPath(path, amount)
    }
}