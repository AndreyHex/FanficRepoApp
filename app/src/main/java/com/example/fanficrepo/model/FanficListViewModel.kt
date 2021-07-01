package com.example.fanficrepo.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fanficrepo.entity.Chapter
import com.example.fanficrepo.entity.Fanfic
import com.example.fanficrepo.service.FanficApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FanficListViewModel : ViewModel() {

    private val _fanficList = MutableLiveData<List<Fanfic>>()
    val fanficList: LiveData<List<Fanfic>> = _fanficList

    private val _chapterList = MutableLiveData<List<Chapter>>()
    val chapterList: LiveData<List<Chapter>> = _chapterList

    init {
        getFanfics()
    }

    private fun getFanfics() {
        FanficApi.retrofitService.fetchAllFanfics().enqueue(object: Callback<List<Fanfic>>{
            override fun onResponse(call: Call<List<Fanfic>>, response: Response<List<Fanfic>>) {
                _fanficList.value = response.body()
            }

            override fun onFailure(call: Call<List<Fanfic>>, t: Throwable) {
                Log.d("FanficViewModel", "getFanfics: failure")
            }
        })
    }

    fun loadChapters(fanficId: Int) {
        FanficApi.retrofitService.fetchChapters(fanficId).enqueue(object: Callback<List<Chapter>>{
            override fun onResponse(call: Call<List<Chapter>>, response: Response<List<Chapter>>) {
                _chapterList.value = response.body()
                Log.d("ApiService", _chapterList.value.toString())
            }

            override fun onFailure(call: Call<List<Chapter>>, t: Throwable) {
                Log.d("FanficViewModel", "loadChapters: failure")
            }
        })
    }


}