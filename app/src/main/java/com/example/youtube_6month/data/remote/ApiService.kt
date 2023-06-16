package com.example.youtube_6month.data.remote

import androidx.core.location.LocationRequestCompat.Quality
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part")part:String,
        @Query("channelId")channelId:String,
        @Query("key")apiKey:String,
    ):Call<Playlists>

    @GET("playlistItems")
    fun getPlaylistItems (
        @Query("key") key:String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String
    ):Call<PlaylistItem>
}