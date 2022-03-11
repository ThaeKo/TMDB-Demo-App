package com.mm.samplemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mm.samplemovieapp.databinding.ItemUpcomingBinding
import com.mm.samplemovieapp.delegates.RecyclerDelegate
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.viewholders.BaseViewHolder
import com.mm.samplemovieapp.viewholders.UpComingViewHolder
import com.mm.samplemovieapp.viewmodels.MainViewModel

class UpComingAdapter(delegate : RecyclerDelegate,viewModel : MainViewModel) : BaseAdapter<UpComingViewHolder, UpcomingListVO>() {

    var mDelegate = delegate
    var mMainViewModel = viewModel

    private var _binding : ItemUpcomingBinding?= null
    private val binding get() = _binding!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UpcomingListVO> {
        _binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpComingViewHolder(binding,mDelegate,mMainViewModel)
    }

}