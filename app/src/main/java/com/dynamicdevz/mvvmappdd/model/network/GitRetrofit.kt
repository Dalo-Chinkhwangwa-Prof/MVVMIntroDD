package com.dynamicdevz.mvvmappdd.model.network

import com.dynamicdevz.mvvmappdd.model.GitResponse
import com.dynamicdevz.mvvmappdd.util.Constants.Companion.BASE_URL
import com.dynamicdevz.mvvmappdd.util.Constants.Companion.END_POINT
import com.dynamicdevz.mvvmappdd.util.Constants.Companion.USER_PATH
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//https://api.github.com/users/Dalo-Chinkhwangwa-Prof/repos
class GitRetrofit {
    private val gitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitService::class.java)//GitService.java

    fun getRepos(userName: String) = gitService.getRepositories(userName)

    interface GitService {
        @GET(END_POINT)
        fun getRepositories(@Path(USER_PATH) username: String): Call<GitResponse>
    }
}