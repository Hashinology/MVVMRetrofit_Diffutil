package com.hashinology.mvvmretrofit_diffutil.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hashinology.mvvmretrofit_diffutil.data.RetrofitClient
import com.hashinology.mvvmretrofit_diffutil.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    private val _getMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val getMovie: LiveData<List<Movie>> = _getMovie

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

init {
    getMovieList()
}

    private fun getMovieList() {
        RetrofitClient.getInstance().getMoviesList().enqueue(object : Callback<List<Movie>?> {
            override fun onResponse(call: Call<List<Movie>?>, response: Response<List<Movie>?>) {
                if (response.isSuccessful){
                    _getMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Movie>?>, t: Throwable) {
                _errorMsg.postValue(t.message)
            }
        })

    }
}