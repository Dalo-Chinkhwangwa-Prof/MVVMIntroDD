package com.dynamicdevz.mvvmappdd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevz.mvvmappdd.model.GitResponse
import com.dynamicdevz.mvvmappdd.model.GitResponseItem
import com.dynamicdevz.mvvmappdd.model.network.GitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel: ViewModel() {
    //LiveData - can not be changed
    //MutableLiveData - can be changed
    val liveData = MutableLiveData<Int>()
    val gitLiveData = MutableLiveData<List<GitResponseItem>>()
    private val gitRetrofit = GitRetrofit()

    fun searchRepositories(userName: String) {

        gitRetrofit.getRepos(userName).enqueue(
            object: Callback<GitResponse>{
                override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                    Log.d("TAG_X", "12")
                    response.body()?.let {
                        gitLiveData.postValue(it)
                    }
                }

                override fun onFailure(call: Call<GitResponse>, t: Throwable) {

                    Log.d("TAG_X", t.localizedMessage)
                }

            }
        )


    }


//    init {
//        Thread(){
//
//            for(i in 1..1000){
//                Thread.sleep(1000)
//                //PostValue -> asynchronously, should be called if on separate thread
//                //SetValue -> sequentially, should be called if on main thread
//                liveData.postValue(i)
//            }
//
//        }.start()
//    }
}