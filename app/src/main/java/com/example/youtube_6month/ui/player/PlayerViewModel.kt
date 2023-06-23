package com.example.youtube_6month.ui.player

import androidx.lifecycle.LiveData
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.data.remote.model.Videos
import com.example.youtube_6month.repository.Repository

class PlayerViewModel(private val repository: Repository) : BaseViewModel() {

    fun getVideos(id: String?): LiveData<Resource<Videos>> {
        return repository.getVideos(id!!)
    }
}