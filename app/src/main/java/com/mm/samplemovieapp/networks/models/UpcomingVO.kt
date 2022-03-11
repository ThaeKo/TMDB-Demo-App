package com.mm.samplemovieapp.networks.models


import com.google.gson.annotations.SerializedName

data class UpcomingVO(
    val dates: Dates,
    val page: Int,
    val results: List<UpcomingListVO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)