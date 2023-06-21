package com.example.youtube_6month.ui.playlists

import androidx.lifecycle.LiveData

import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.data.remote.model.Playlists
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.data.remote.RemoteDataSource
import com.example.youtube_6month.repository.Repository

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<Resource<Playlists>> {
        return repository.getPlaylist()
    }

}