package com.englishgalaxy.traveltestapp

import com.englishgalaxy.traveltestapp.net.NetRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MyTest {
    val api = NetRepository()

    @Test
    fun testApi() = runBlocking {
        val res = api.getItemPlaces()
        Assert.assertNotNull(res)
    }
}