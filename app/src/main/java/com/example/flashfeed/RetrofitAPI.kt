package com.example.flashfeed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {
    @GET
    fun getAllNews(@Url url: String?): Call<NewsModal>

    @GET
    fun getNewsByCatergory(@Url url: String?): Call<NewsModal>
}