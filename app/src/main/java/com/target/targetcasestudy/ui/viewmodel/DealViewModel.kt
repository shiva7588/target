package com.target.targetcasestudy.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.target.targetcasestudy.database.dao.DealsDAO
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.database.entity.Products
import com.target.targetcasestudy.dataflow.Resource
import com.target.targetcasestudy.repository.TargetRepository
import com.target.targetcasestudy.repository.TargetRepositoryImpl
import com.target.targetcasestudy.ui.base.BaseViewModel
import com.target.targetcasestudy.webapi.DealsApi
import javax.inject.Inject

class DealViewModel @Inject constructor(
    dao: DealsDAO,
    dealsApi: DealsApi
) : BaseViewModel() {
    private val repository: TargetRepository = TargetRepositoryImpl(dao, dealsApi)
    private val liveData = MutableLiveData<Resource<Products>>()

    @SuppressLint("CheckResult")
    fun getRepo(force: Boolean) {
        repository.getDeals(force)
            .doOnSubscribe { disposable -> addToDisposable(disposable) }
            .subscribe { resource -> getDealsLiveData().postValue(resource) }
    }

    fun getDealsLiveData() = liveData
}