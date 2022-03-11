package com.mm.samplemovieapp.networks.models

data class PopularVO(
    val page: Int,
    val results: List<UpcomingListVO>
)