package com.englishgalaxy.traveltestapp.di

import com.englishgalaxy.traveltestapp.net.NetRepository
import com.englishgalaxy.traveltestapp.viewmodels.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapModule = module {
    single { NetRepository() }
    viewModel { MapViewModel(get()) }
}