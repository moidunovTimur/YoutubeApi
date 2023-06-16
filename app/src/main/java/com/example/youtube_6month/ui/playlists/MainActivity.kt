package com.example.youtube_6month.ui.playlists

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_6month.core.network.results.Status
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.databinding.ActivityMainBinding
import com.example.youtube_6month.data.remote.model.Item
import com.example.youtube_6month.ui.playlists.adapter.MainAdapter
import com.example.youtube_6month.ui.detail.DetailActivity
import com.example.youtube_6month.core.utils.ConnectionLiveData

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(){

    private val adapter = MainAdapter(this::onClick)

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    override fun setupLiveData() {
        super.setupLiveData()

   viewModel.loading.observe(this) {
     binding.progressBar.isVisible = it
}

        viewModel.getPlayList().observe(this){
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.items?.let { it1 -> adapter.setList(it1) }

                    viewModel.loading.postValue(false)
                }
                Status.ERROR -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)

                }
            }
        }
    }

    private fun onClick(item: Item){
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        startActivity(intent)


        }
    }

