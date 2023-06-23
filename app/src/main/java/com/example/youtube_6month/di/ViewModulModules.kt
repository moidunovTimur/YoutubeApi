package com.example.youtube_6month.di

import com.example.youtube_6month.ui.detail.DetailViewModel
import com.example.youtube_6month.ui.player.PlayerViewModel
import com.example.youtube_6month.ui.playlists.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { PlayerViewModel(get()) }
}