package com.mm.samplemovieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mm.samplemovieapp.R
import com.mm.samplemovieapp.database.PopularTable
import com.mm.samplemovieapp.databinding.ItemRecommendedBinding
import com.mm.samplemovieapp.delegates.RecyclerDelegate
import com.mm.samplemovieapp.networks.Constants
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.viewmodels.MainViewModel

class PopularViewHolder (val binding:ItemRecommendedBinding,
                         var mDelegate : RecyclerDelegate,
var mViewModel : MainViewModel) : BaseViewHolder<UpcomingListVO>(binding.root) {

    override fun setData(data: UpcomingListVO) {
        binding.apply {
            tvMovieName.text = data.title
            tvFavCount.text = data.voteCount.toString()

            mViewModel.insertPopular(
                PopularTable(0,data.id,Gson().toJson(data))
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
                mViewModel.updatePopular(
                    data.id,Gson().toJson(data)
                )
                mDelegate.onTapFavPopularItem(data)
            }

            this.root.setOnClickListener {
                mDelegate.onTapPopularItem(data)
            }
        }
    }

    override fun onClick(v: View?) {

    }
}