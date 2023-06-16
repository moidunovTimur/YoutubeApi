package com.example.youtube_6month.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_6month.App.Companion.repository
import com.example.youtube_6month.BuildConfig
import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.data.remote.model.Playlists
import com.example.youtube_6month.data.remote.ApiService
import com.example.youtube_6month.core.network.RetrofitClient
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.utils.channelId
import com.example.youtube_6month.core.utils.part
import com.example.youtube_6month.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {


    fun getPlayList(): LiveData<Resource<Playlists>> {
        return repository.getPlaylist()
    }

}