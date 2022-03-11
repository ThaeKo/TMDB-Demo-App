package com.mm.samplemovieapp.networks.repositorys

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mm.samplemovieapp.networks.RetroServiceInstance
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.google.gson.Gson
import com.mm.samplemovieapp.networks.models.UpcomingVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UpcomingRepository @Inject constructor(private val retroInstance: RetroServiceInstance) {

    fun upcomingApiCall(liveData: MutableLiveData<List<UpcomingListVO>>){
        val call: Call<UpcomingVO> = retroInstance.getUpcomingList(
            "f554ce4a81b3cc2f7cec6e717cef1319",
            1
        )
        call?.enqueue(object : Callback<UpcomingVO> {
            override fun onFailure(call: Call<UpcomingVO>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
                liveData.postValue(null)
            }

            override fun onResponse(call: Call<UpcomingVO>, response: Response<UpcomingVO>) {
                Log.e("ERROR",Gson().toJson(response.body()))
                liveData.postValue(response.body()?.results)
            }
        })
    }
}