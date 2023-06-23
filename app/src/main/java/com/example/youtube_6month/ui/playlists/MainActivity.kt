package com.example.youtube_6month.ui.playlists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_6month.core.network.results.Status
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.databinding.ActivityMainBinding
import com.example.youtube_6month.data.remote.model.Item
import com.example.youtube_6month.ui.playlists.adapter.MainAdapter
import com.example.youtube_6month.ui.detail.DetailActivity
import com.example.youtube_6month.core.utils.ConnectionLiveData
import com.example.youtube_6month.data.remote.model.Playlists
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: MainAdapter

    override val viewModel: MainViewModel by viewModel()


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

    override fun setUI() {
        super.setUI()
        adapter = MainAdapter(this::onClick)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.getPlayList().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.items?.let { it1 -> adapter.setList(it1) }

                    viewModel.loading.postValue(false)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)

                }
            }
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecycler() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        adapter.notifyDataSetChanged()
    }


    companion object {
        const val ID = "ID"
        const val KEY_FOR_COUNT = "ded"
    }
}

