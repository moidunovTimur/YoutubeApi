package com.example.youtube_6month.ui.detail

import android.content.Intent
import android.view.View
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.core.utils.ConnectionLiveData
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.databinding.ActivityDetailBinding
import com.example.youtube_6month.ui.detail.adapter.DetailAdapter
import com.example.youtube_6month.ui.player.PlayerActivity
import com.example.youtube_6month.ui.playlists.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DetailAdapter
    override val viewModel: DetailViewModel by viewModel()

    override fun initClickListener() {
        super.initClickListener()
        binding.backTv.setOnClickListener { finish() }

    }

    override fun setupLiveData() {
        super.setupLiveData()
        val id = intent.getStringExtra(MainActivity.ID)
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        val count = intent.getIntExtra("count", 0)

        viewModel.getPlayListItems(id).observe(this) {
            it.data?.let { it1 -> adapter.addList(it1.items) }
            binding.tvTitle.text = title
            binding.tvDesc.text = desc
            binding.countVideos.text = "$count video series"
        }

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

    override fun setUI() {
        super.setUI()
        adapter = DetailAdapter(this::onClick)
        binding.rvDetail.adapter = adapter
    }

    private fun onClick(item: PlaylistItem.Item) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("id1", item.contentDetails?.videoId)
        intent.putExtra("title1", item.snippet?.title)
        intent.putExtra("desc1", item.snippet?.description)
        startActivity(intent)

    }

}