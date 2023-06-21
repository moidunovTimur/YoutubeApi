package com.example.youtube_6month.di

import com.example.youtube_6month.ui.playlists.MainViewModel
import org.koin.dsl.module

val viewModelModules = module {
    factory { MainViewModel(repository = get()) }
}