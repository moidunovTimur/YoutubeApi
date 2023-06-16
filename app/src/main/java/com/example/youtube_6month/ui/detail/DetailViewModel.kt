package com.example.youtube_6month.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube_6month.App.Companion.repository
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.data.remote.model.PlaylistItem

class DetailViewModel : BaseViewModel() {

    fun getPlayListItems(playlistId:String?): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItems(playlistId!!)
    }

}