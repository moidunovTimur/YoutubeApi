package com.example.youtube_6month.ui.detail

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.network.results.Status
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.core.utils.ConnectionLiveData
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.databinding.ActivityDetailBinding
import com.example.youtube_6month.ui.detail.adapter.DetailAdapter

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DetailAdapter


    override fun initClickListener() {
        super.initClickListener()
        binding.backTv.setOnClickListener { finish() }
    }

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun setupLiveData() {
        super.setupLiveData()
        val id = intent.getStringExtra("id")
        intent.getStringExtra("title")
        intent.getStringExtra("desc")
        viewModel.loading.observe(this){
            binding.progressBar.isVisible = it
        }

        viewModel.getPlayListItems(id).observe(this) {
           when(it.status){
               Status.SUCCESS->{
                   binding.rvDetail.adapter = adapter
                   it.data?.let { it1 -> adapter.addList(it1.items) }
                   viewModel.loading.postValue(false)
               }
                  Status.ERROR->{
                   Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                   viewModel.loading.postValue(false)
               }
               Status.LOADING->{
                   viewModel.loading.postValue(true)
               }
           }
        }

        adapter = DetailAdapter()
        binding.rvDetail.adapter = adapter
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

}