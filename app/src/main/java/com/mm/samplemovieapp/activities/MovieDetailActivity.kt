package com.mm.samplemovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mm.samplemovieapp.R
import com.mm.samplemovieapp.databinding.ActivityMovieDetailBinding
import com.mm.samplemovieapp.networks.Constants
import com.mm.samplemovieapp.networks.models.UpcomingListVO

class MovieDetailActivity : BaseActivity() {

    lateinit var binding : ActivityMovieDetailBinding

    companion object{
        var mData : UpcomingListVO ?= null
        fun newInstance(mContext : Context,data : UpcomingListVO) : Intent{
            mData = data
            return Intent(mContext,MovieDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            Glide.with(this@MovieDetailActivity)
                .load("${Constants.IMAGE_URL}${mData?.backdropPath}")
                .placeholder(R.drawable.guitar)
                .into(ivDetail)
            tvMovieName.text = mData?.title
            tvMovieDesc.text = mData?.overview
        }
    }

}