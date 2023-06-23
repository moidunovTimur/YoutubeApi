package com.example.youtube_6month.data.remote

import android.provider.MediaStore.Video
import com.example.youtube_6month.BuildConfig.API_KEY
import com.example.youtube_6month.core.network.results.BaseDataSource
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.utils.channelId
import com.example.youtube_6month.core.utils.part
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Videos

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

     suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(
            part,
            channelId,
            API_KEY
        )


    }

    suspend fun getPlaylistItems(playlistId: String): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylistItems(
                API_KEY,
                part,
                playlistId
            )
        }
    }

    suspend fun getVideos(id: String?): Resource<Videos> {
        return getResult {
            apiService.getVideos(
                API_KEY,
                part,
                id!!
            )
        }
    }
}





