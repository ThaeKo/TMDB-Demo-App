package com.mm.samplemovieapp.activities

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mm.samplemovieapp.R
import com.mm.samplemovieapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.google.gson.Gson
import com.mm.samplemovieapp.adapters.CategoryVPAdapter
import com.mm.samplemovieapp.databinding.ActivityMovieMainBinding
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMovieMainBinding

    companion object{
        fun newInstance(mContext : Context) : Intent{
            val mIntent = Intent(mContext,MainActivity::class.java)
            mIntent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            return mIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            val mAdapter = CategoryVPAdapter(supportFragmentManager,this@MainActivity,setUpData())
            vpMovie.adapter = mAdapter
            tabMovie.setupWithViewPager(vpMovie)
        }
    }

    fun setUpData() : MutableList<String>{
        val list = mutableListOf<String>()
        list.add("Movie")
        list.add("Sport")
        list.add("News")
        list.add("Story")

        return list
    }


}