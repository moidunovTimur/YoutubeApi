package com.example.youtube_6month.remote

import com.example.youtube_6month.model.Playlists
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
}