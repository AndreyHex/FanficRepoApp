package com.example.fanficrepo.service

import com.example.fanficrepo.entity.Chapter
import com.example.fanficrepo.entity.Fanfic
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://fanfic-repo.herokuapp.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FanficApiService {
    @GET("fanfics")
    fun fetchAllFanfics() : Call<List<Fanfic>>

    @GET("chapters/{ffId}")
    fun fetchChapters(@Path("ffId") ffId: Int) : Call<List<Chapter>>
}

object FanficApi{
    val retrofitService: FanficApiService by lazy { retrofit.create(FanficApiService::class.java) }
}