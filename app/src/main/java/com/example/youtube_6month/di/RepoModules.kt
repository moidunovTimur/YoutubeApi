package com.example.youtube_6month.di

import com.example.youtube_6month.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModules:Module = module {
   single { Repository(remoteDataSource = get ()) }
}


