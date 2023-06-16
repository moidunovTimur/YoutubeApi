package com.example.youtube_6month.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_6month.BuildConfig
import com.example.youtube_6month.core.network.RetrofitClient
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.utils.channelId
import com.example.youtube_6month.core.utils.part
import com.example.youtube_6month.data.remote.ApiService
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylist(): LiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()

        apiService.getPlaylists(
            part,
            channelId,
            BuildConfig.API_KEY,
        ).enqueue(object : Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())

                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
            }
        })
        return data
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        val data = MutableLiveData<Resource<PlaylistItem>>()

        data.value = Resource.loading()

        apiService.getPlaylistItems(
            BuildConfig.API_KEY,
            part,
            playlistId,
        ).enqueue(object : Callback<PlaylistItem> {
            override fun onResponse(call: Call<PlaylistItem>, response: Response<PlaylistItem>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())

                }
            }

            override fun onFailure(call: Call<PlaylistItem>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
            }
        })
        return data
    }
}