package com.mm.samplemovieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mm.samplemovieapp.R
import com.mm.samplemovieapp.database.UpcomingTable
import com.mm.samplemovieapp.databinding.ItemUpcomingBinding
import com.mm.samplemovieapp.delegates.RecyclerDelegate
import com.mm.samplemovieapp.networks.Constants
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.viewmodels.MainViewModel

class UpComingViewHolder (val binding:ItemUpcomingBinding,
                          var mDelegate : RecyclerDelegate,
var mViewModel : MainViewModel) : BaseViewHolder<UpcomingListVO>(binding.root) {

    override fun setData(data: UpcomingListVO) {
        binding.apply {
            tvMovieName.text = data.title
            tvMovieDesc.text = data.overview
            tvFavCount.text = data.voteCount.toString()

            mViewModel.insertUpComing(
                UpcomingTable(0,data.id,Gson().toJson(data))
            )

            if(data.favourite){
                ivFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            }else{
                ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            Glide.with(this.root.context)
                .load("${Constants.IMAGE_URL}${data.posterPath}")
                .placeholder(R.drawable.guitar)
                .into(ivImage)

            ivFav.setOnClickListener {
                data.favourite = !data.favourite!!
                mViewModel.updateUpComing(
                    data.id,Gson().toJson(data)
                )
                mDelegate.onTapFavUpComingItem(data)
            }

            this.root.setOnClickListener {
                mDelegate.onTapUpComingItem(data)
            }
        }
    }

    override fun onClick(v: View?) {

    }
}