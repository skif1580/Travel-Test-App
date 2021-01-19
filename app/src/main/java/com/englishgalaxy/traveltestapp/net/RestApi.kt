package com.englishgalaxy.traveltestapp.net

import com.englishgalaxy.traveltestapp.net.responce.ItemPlaces
import retrofit2.http.GET

interface RestApi {

    @GET("places")
    suspend fun getItem(): ItemPlaces
}