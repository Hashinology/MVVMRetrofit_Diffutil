package com.hashinology.mvvmretrofit_diffutil.data

import com.hashinology.mvvmretrofit_diffutil.models.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {
    @GET("movielist.json")
    fun getMoviesList(): Call<List<Movie>>

    companion object{
        var instance: RetrofitClient? = null

        @JvmName("getInstance1")
        fun getInstance(): RetrofitClient{
            if(instance == null){
                var retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

                instance = retrofit.create(RetrofitClient::class.java)
            }
            return instance!!
        }
    }
}