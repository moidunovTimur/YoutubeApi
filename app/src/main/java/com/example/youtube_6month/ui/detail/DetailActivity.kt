package com.example.youtube_6month.ui.detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.core.utils.ConnectionLiveData
import com.example.youtube_6month.databinding.ActivityDetailBinding
import com.example.youtube_6month.ui.detail.adapter.DetailAdapter

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
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
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")


        viewModel.getPlayListItems(id).observe(this) {
            it.data?.let { it1 -> adapter.addList(it1.items) }
            binding.tvTitle.text = title
            binding.tvDesc.text = desc
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