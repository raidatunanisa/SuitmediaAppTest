package com.suitmedia.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*

class ThirdActivityViewModel: ViewModel() {

    fun getUsers(): LiveData<PagingData<DataItem>> {
        val apiService = ApiConfig.getApiService()
        return Pager(
            config = PagingConfig(
                pageSize = 9
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService)
            }
        ).liveData.cachedIn(viewModelScope)
    }
}