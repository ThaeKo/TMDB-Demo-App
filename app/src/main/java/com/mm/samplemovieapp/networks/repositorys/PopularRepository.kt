package com.mm.samplemovieapp.networks.repositorys

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mm.samplemovieapp.networks.RetroServiceInstance
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.google.gson.Gson
import com.mm.samplemovieapp.networks.models.PopularVO
import com.mm.samplemovieapp.networks.models.UpcomingVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PopularRepository @Inject constructor(private val retroInstance: RetroServiceInstance) {

    fun popularApiCall(liveData: MutableLiveData<List<UpcomingListVO>>){
        val call: Call<PopularVO> = retroInstance.getPopularLIst(
            "f554ce4a81b3cc2f7cec6e717cef1319",
            1
        )
        call?.enqueue(object : Callback<PopularVO> {
            override fun onFailure(call: Call<PopularVO>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
                liveData.postValue(null)
            }

            override fun onResponse(call: Call<PopularVO>, response: Response<PopularVO>) {
                Log.e("ERROR",Gson().toJson(response.body()))
                liveData.postValue(response.body()?.results)
            }
        })
    }
}