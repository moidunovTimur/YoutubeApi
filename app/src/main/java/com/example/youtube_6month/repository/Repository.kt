package com.example.youtube_6month.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.data.remote.RemoteDataSource
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Playlists
import com.example.youtube_6month.data.remote.model.Videos
import kotlinx.coroutines.Dispatchers

class Repository(private val remoteDataSource: RemoteDataSource) {


    fun getPlaylist(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getPlaylists()
        emit(response)
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = remoteDataSource.getPlaylistItems(playlistId)
            emit(response)
        }

    fun getVideos(id: String): LiveData<Resource<Videos>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getVideos(id)
        emit(response)
    }
}

