package com.mm.samplemovieapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.Resource
import com.google.gson.Gson
import com.mm.samplemovieapp.activities.MovieDetailActivity
import com.mm.samplemovieapp.adapters.PopularAdapter
import com.mm.samplemovieapp.adapters.UpComingAdapter
import com.mm.samplemovieapp.databinding.FragmentMovieBinding
import com.mm.samplemovieapp.delegates.RecyclerDelegate
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment(),RecyclerDelegate {

    private var _binding : FragmentMovieBinding ?= null
    private val binding get() = _binding!!
    lateinit var mView: View
    lateinit var mAdapter : UpComingAdapter
    lateinit var mPopularAdapter : PopularAdapter
    lateinit var viewModel : MainViewModel
    private var mUpComingList : MutableList<UpcomingListVO> = mutableListOf()
    private var mPopularList : MutableList<UpcomingListVO> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater,container,false)
        mView = binding.root

        initLayout()
        initViewModel()
        initDatabaseViewModel()

        return mView

    }

    private fun initLayout() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.apply {
            mAdapter = UpComingAdapter(this@MovieFragment,viewModel)
            mPopularAdapter = PopularAdapter(this@MovieFragment,viewModel)
            rvUpcoming.apply {
                layoutManager = LinearLayoutManager(mView.context)
                setHasFixedSize(true)
                adapter = mAdapter
            }

            rvRecommended.apply {
                layoutManager = LinearLayoutManager(mView.context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = mPopularAdapter
            }
        }
    }

    private fun initDatabaseViewModel() {
        viewModel.getLiveDbUpcomingObserver().observe(viewLifecycleOwner,{
            if(it != null){
                convertUpComingGson(it)
            }
        })

        viewModel.getLiveDbPopularObserver().observe(viewLifecycleOwner,{
            if(it != null){
                convertPopularGson(it)
            }
        })
        viewModel.loadDbPopularListOfData()
        viewModel.loadDbUpComingListOfData()
    }

    private fun convertUpComingGson(data: List<String>) {
        if (mUpComingList.size > 0){
            mUpComingList.clear()
        }
        for(i in data.indices){
            mUpComingList.add(Gson().fromJson(data[i],UpcomingListVO::class.java))
        }
    }

    private fun convertPopularGson(data: List<String>) {
        if (mPopularList.size > 0){
            mPopularList.clear()
        }
        for(i in data.indices){
            mPopularList.add(Gson().fromJson(data[i],UpcomingListVO::class.java))
        }
    }



    private fun initViewModel() {

        viewModel.getLiveDataUpcomingObserver().observe(viewLifecycleOwner,{
            if(it != null){
                if(it.size > 0) {
                    mAdapter.setNewData(it as MutableList)
                }else{
                    mAdapter.setNewData(mUpComingList)
                }
            }else{

                if(mUpComingList.size > 0){
                    mAdapter.setNewData(mUpComingList)
                    Toast.makeText(mView.context,"Showing is offline data", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(mView.context,"Error is getting data", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getLiveDataPopularObserver().observe(viewLifecycleOwner,{
            if(it != null){
                if(it.size > 0) {
                    mPopularAdapter.setNewData(it as MutableList)
                }else{
                    mPopularAdapter.setNewData(mPopularList)
                }
            }else{

                if(mPopularList.size > 0){
                    mPopularAdapter.setNewData(mPopularList)
                    Toast.makeText(mView.context,"Showing is offline data", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(mView.context,"Error is getting data", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.loadUpComingListOfData()
        viewModel.loadPopularListOfData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onTapUpComingItem(data: UpcomingListVO) {
        startActivity(MovieDetailActivity.newInstance(mView.context,data))
    }

    override fun onTapFavUpComingItem(data: UpcomingListVO) {
        Toast.makeText(mView.context,"${data.favourite}",Toast.LENGTH_SHORT).show()
        mAdapter.notifyDataSetChanged()
    }

    override fun onTapPopularItem(data: UpcomingListVO) {
        startActivity(MovieDetailActivity.newInstance(mView.context,data))
    }

    override fun onTapFavPopularItem(data: UpcomingListVO) {
        Toast.makeText(mView.context,"${data.favourite}",Toast.LENGTH_SHORT).show()
        mPopularAdapter.notifyDataSetChanged()
    }

}