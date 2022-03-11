package com.mm.samplemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mm.samplemovieapp.databinding.ItemRecommendedBinding
import com.mm.samplemovieapp.delegates.RecyclerDelegate
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.viewholders.BaseViewHolder
import com.mm.samplemovieapp.viewholders.PopularViewHolder
import com.mm.samplemovieapp.viewmodels.MainViewModel

class PopularAdapter(delegate : RecyclerDelegate,viewModel : MainViewModel) : BaseAdapter<PopularViewHolder, UpcomingListVO>() {

    var mDelegate = delegate
    var mMainViewModel = viewModel

    private var _binding : ItemRecommendedBinding?= null
    private val binding get() = _binding!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UpcomingListVO> {
        _binding = ItemRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularViewHolder(binding,mDelegate,mMainViewModel)
    }

}