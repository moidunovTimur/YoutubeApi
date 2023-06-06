package com.example.youtube_6month.ui

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_6month.R
import com.example.youtube_6month.base.BaseActivity
import com.example.youtube_6month.databinding.ActivityDetailBinding
import com.example.youtube_6month.databinding.ActivityMainBinding
import com.example.youtube_6month.ui.adapter.MainAdapter

class MainActivity : BaseActivity<ActivityMainBinding> (){

    private val adapter = MainAdapter(this::onClick)

    private val viewModel:MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


    override fun setupLiveData() {
        super.setupLiveData()

        viewModel.getPlayList().observe(this){
            Toast.makeText(this,it.kind.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClick(item:Item){
        val intent = Intent(this@MainActivity,DetailActivity::class.java )
        intent.putExtra("key", item.snippet.id)
        Toast.makeText(this, item.snippet.id, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}