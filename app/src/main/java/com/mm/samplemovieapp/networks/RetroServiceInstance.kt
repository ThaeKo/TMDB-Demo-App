package com.mm.samplemovieapp.networks

import com.mm.samplemovieapp.networks.models.PopularVO
import com.mm.samplemovieapp.networks.models.UpcomingVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInstance {

    @GET("movie/upcoming")
    fun getUpcomingList(
        @Query("api_key") api_key: String?,
        @Query("page") page: Int?,

    ): Call<UpcomingVO>

    @GET("movie/popular")
    fun getPopularLIst(
        @Query("api_key") api_key: String?,
        @Query("page") page: Int?,

    ): Call<PopularVO>


}