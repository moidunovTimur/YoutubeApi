package com.example.youtube_6month.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkInternet()
        setUI()
        setupLiveData()
        initClickListener()

    }

    open fun setupLiveData() {} // инициализация Live data

    open fun setUI() {} // инициализация UI

    open fun initClickListener() {} // внутру этого метода обрабатываем все клики

    open fun checkInternet() {} // внутру этого метода проверяем интернет


}
