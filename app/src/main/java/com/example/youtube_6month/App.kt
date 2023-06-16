package com.example.youtube_6month

import android.app.Application
import com.example.youtube_6month.repository.Repository

class App:Application() {

    companion object {

        val repository = Repository()
    }
}