package com.mm.samplemovieapp.database

import androidx.lifecycle.MutableLiveData
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val videoDao: VideoDao){

    fun getUpcomingList(liveData: MutableLiveData<List<String>>){
        val mData = videoDao.getUpComingList()
        if(mData.isNotEmpty()) {
            liveData.postValue(mData)
        }else{
            liveData.postValue(null)
        }
    }

    fun getPopularList(liveData: MutableLiveData<List<String>>){
        val mData = videoDao.getPopularList()
        if(mData.isNotEmpty()) {
            liveData.postValue(mData)
        }else{
            liveData.postValue(null)
        }
    }

    fun insertUser(data : UserTable){
        videoDao.insertUser(data)
    }

    fun insertPopular(data : PopularTable){
        videoDao.insertPopular(data)
    }

    fun insertUpcoming(data : UpcomingTable){
        videoDao.insertUpcoming(data)
    }

    fun updateUpcoming(id : Int,data : String){
        videoDao.updateUpcoming(id,data)
    }

    fun updatePopular(id : Int,data : String){
        videoDao.updatePopular(id,data)
    }

}