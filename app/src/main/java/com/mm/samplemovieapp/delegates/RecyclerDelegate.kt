package com.mm.samplemovieapp.delegates

import com.mm.samplemovieapp.networks.models.UpcomingListVO


interface RecyclerDelegate {
    fun onTapUpComingItem(data : UpcomingListVO)

    fun onTapFavUpComingItem(data : UpcomingListVO)

    fun onTapPopularItem(data : UpcomingListVO)

    fun onTapFavPopularItem(data : UpcomingListVO)
}