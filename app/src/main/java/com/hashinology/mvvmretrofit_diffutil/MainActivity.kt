package com.hashinology.mvvmretrofit_diffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashinology.mvvmretrofit_diffutil.adapter.MovieAdapter
import com.hashinology.mvvmretrofit_diffutil.databinding.ActivityMainBinding
import com.hashinology.mvvmretrofit_diffutil.extension.toast
import com.hashinology.mvvmretrofit_diffutil.ui.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        setRecyclerView()

        movieViewModel.getMovie.observe(this, Observer {
            movieAdapter.differ.submitList(it)
        })

        movieViewModel.errorMsg.observe(this, Observer {
            toast(this, it)
        })
    }

    private fun setRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}