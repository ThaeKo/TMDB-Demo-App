package com.mm.samplemovieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mm.samplemovieapp.networks.models.UpcomingListVO
import com.mm.samplemovieapp.networks.repositorys.PopularRepository
import com.mm.samplemovieapp.networks.repositorys.UpcomingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.mm.samplemovieapp.database.DatabaseRepository
import com.mm.samplemovieapp.database.PopularTable
import com.mm.samplemovieapp.database.UpcomingTable
import com.mm.samplemovieapp.database.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UpcomingRepository,
    private val popularRepository: PopularRepository,
    private val databaseRepository : DatabaseRepository
) : ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<UpcomingListVO>>
    lateinit var livePopularList: MutableLiveData<List<UpcomingListVO>>
    lateinit var dbUpComingList : MutableLiveData<List<String>>
    lateinit var dbPopularList : MutableLiveData<List<String>>

    init {
        liveDataList = MutableLiveData()
        livePopularList = MutableLiveData()

        dbPopularList = MutableLiveData()
        dbUpComingList = MutableLiveData()
    }

    fun getLiveDataUpcomingObserver(): MutableLiveData<List<UpcomingListVO>> {
        return liveDataList
    }

    fun getLiveDataPopularObserver(): MutableLiveData<List<UpcomingListVO>> {
        return livePopularList
    }

    fun loadUpComingListOfData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upcomingApiCall(liveDataList)
        }
    }

    fun loadPopularListOfData() {
        viewModelScope.launch(Dispatchers.IO) {
            popularRepository.popularApiCall(livePopularList)
        }
    }

    //data base
    fun getLiveDbUpcomingObserver() : MutableLiveData<List<String>>{
        return dbUpComingList
    }

    fun getLiveDbPopularObserver() : MutableLiveData<List<String>>{
        return dbPopularList
    }

    fun loadDbUpComingListOfData() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getUpcomingList(dbUpComingList)
        }
    }

    fun loadDbPopularListOfData() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getPopularList(dbPopularList)
        }
    }

    fun insertUser(data : UserTable){
        databaseRepository.insertUser(data)
    }

    fun insertUpComing(data : UpcomingTable){
        databaseRepository.insertUpcoming(data)
    }

    fun insertPopular(data : PopularTable){
        databaseRepository.insertPopular(data)
    }

    fun updateUpComing(id : Int,data : String){
        databaseRepository.updateUpcoming(id,data)
    }

    fun updatePopular(id : Int,data : String){
        databaseRepository.updatePopular(id,data)
    }

}