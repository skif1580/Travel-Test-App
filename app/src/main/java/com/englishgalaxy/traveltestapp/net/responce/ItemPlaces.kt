package com.englishgalaxy.traveltestapp.net.responce

import kotlinx.serialization.Serializable

@Serializable
data class ItemPlaces(
    val places: List<Place>
)

@Serializable
data class Place(
    val id: Long,
    val name: String,
    val lat: Double,
    val lng: Double
)

