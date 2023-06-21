package com.example.youtube_6month.core.network.results

import com.example.youtube_6month.BuildConfig
import com.example.youtube_6month.data.remote.ApiService
import com.example.youtube_6month.data.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideOkHttpClient() }
  single { provideRetrofit(okHttpClient = get()) }
    factory { provideApi(retrofit = get()) }
    factory { RemoteDataSource(apiService = get()) }
}


 fun provideOkHttpClient():OkHttpClient{
     val interceptor = HttpLoggingInterceptor()
     interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

     return OkHttpClient().newBuilder()
         .connectTimeout(20,TimeUnit.SECONDS)
         .writeTimeout(20,TimeUnit.SECONDS)
         .readTimeout(20,TimeUnit.SECONDS)
         .addInterceptor(interceptor)
         .build()

 }

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}

 fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
