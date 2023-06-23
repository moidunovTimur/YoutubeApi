package com.example.youtube_6month.di

import com.example.youtube_6month.core.network.results.networkModule

val koinModules = listOf(
    repositoryModules,
    networkModule,
    viewModelModules
)


