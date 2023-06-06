package com.example.youtube_6month.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_6month.BuildConfig
import com.example.youtube_6month.model.Playlists
import com.example.youtube_6month.remote.ApiService
import com.example.youtube_6month.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val  apiService:ApiService = RetrofitClient.create()

    fun getPlayList(): LiveData<Playlists>{
        return playlists()
    }

    private fun playlists(): LiveData<Playlists> {

        val  data = MutableLiveData<Playlists>()
        apiService.getPlaylists(
            "snippet contentDetails",
            "UCGie8GMlUo3kBKIopdvumVQ",
            BuildConfig.API_KEY,
        ).enqueue(object:Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful){
                    data.value = response.body()

                }

            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                print(t.stackTrace)

            }

        })
        return data


    }
}