package com.example.youtube_6month.data.remote

import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Playlists
import com.example.youtube_6month.data.remote.model.Videos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
    ): Response<Playlists>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String
    ): Response<PlaylistItem>

    @GET("videos")
    suspend fun getVideos(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") id: String
    ): Response<Videos>
}