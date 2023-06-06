package com.example.youtube_6month.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtube_6month.base.BaseActivity
import com.example.youtube_6month.databinding.ActivityDetailBinding
import com.example.youtube_6month.databinding.ActivityMainBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initClickListener() {
        super.initClickListener()
        var result = intent.getStringExtra("key")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}