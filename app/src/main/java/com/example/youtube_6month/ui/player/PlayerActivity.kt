package com.example.youtube_6month.ui.player

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.youtube_6month.core.ui.BaseActivity
import com.example.youtube_6month.core.utils.ConnectionLiveData
import com.example.youtube_6month.databinding.ActivityPlayerBinding
import com.example.youtube_6month.databinding.DownloadBinding
import com.google.android.exoplayer2.Player
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>(), Player.Listener {

    private lateinit var dialogBinding: DownloadBinding
    override val viewModel: PlayerViewModel by viewModel()

    private fun inflateDialogBinding() {
        dialogBinding = DownloadBinding.inflate(layoutInflater)
    }

    override fun inflateViewBinding(): ActivityPlayerBinding {
        inflateDialogBinding()
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                intent.getStringExtra("id1")?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setUI() {
        super.setUI()
        val getId = intent.getStringExtra("id1")
        val getTitle = intent.getStringExtra("title1")
        val getDesc = intent.getStringExtra("desc1")

        viewModel.getVideos(getId!!).observe(this) {
            binding.tvTitle.text = getTitle
            binding.tvDescription.text = getDesc
        }
    }

    override fun initClickListener() {
        super.initClickListener()
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnDownload.setOnClickListener {
            showDownloadDialog()
        }
    }

    private fun showDownloadDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)

        val dialog = dialogBuilder.create()

        val parentView = dialogBinding.root.parent as? ViewGroup
        parentView?.removeView(dialogBinding.root)

        dialogBinding.btnDownload.setOnClickListener {
            val selectedQuality = when (dialogBinding.radioGroup.checkedRadioButtonId) {
                dialogBinding.radioButton480.id -> "480p"
                dialogBinding.radioButton720.id -> "720p"
                dialogBinding.radioButton1080.id -> "1080p"
                else -> "null"
            }
            Toast.makeText(
                this,
                "Downloading video\nwith quality: $selectedQuality",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()

        }

        dialog.show()
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.clNoInternet.isVisible = false
                binding.llMainLayout.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainLayout.isVisible = false
            }
        }
    }

}
