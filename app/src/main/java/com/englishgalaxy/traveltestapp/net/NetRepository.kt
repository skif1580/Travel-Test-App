package com.englishgalaxy.traveltestapp.net

import com.englishgalaxy.traveltestapp.net.responce.ItemPlaces

class NetRepository {
    private val api = ApiWrapper.api

    suspend fun getItemPlaces(): ItemPlaces = api.getItem()
}