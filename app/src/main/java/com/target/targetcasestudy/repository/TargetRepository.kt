package com.target.targetcasestudy.repository

import com.target.targetcasestudy.dataflow.NetworkBoundResource
import com.target.targetcasestudy.dataflow.Resource
import com.target.targetcasestudy.database.dao.DealsDAO
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.database.entity.Products
import com.target.targetcasestudy.webapi.DealsApi
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*
import javax.inject.Singleton

interface TargetRepository {
    fun getDeals(force: Boolean): Observable<Resource<Products>>
}

@Singleton
class TargetRepositoryImpl(
    private val dealsDAO: DealsDAO,
    private val dealsApi: DealsApi
) : TargetRepository {
    override fun getDeals(force: Boolean): Observable<Resource<Products>> {
        return object : NetworkBoundResource<Products, List<DealEntity>>() {

            override fun saveCallResult(item: List<DealEntity>) {
                dealsDAO.insertDealEntries(item)
            }

            override fun shouldFetch(): Boolean = force

            override fun loadFromDb(): Flowable<Products> {
                var allDeals: List<DealEntity> = dealsDAO.getAllDeals()
                return if (allDeals.isEmpty()) {
                    Flowable.empty()
                } else Flowable.just(Products(allDeals))
            }

            override fun createCall(): Observable<Resource<List<DealEntity>>> {
                return dealsApi.getDeals().flatMap { targetRepository ->
                    Observable.just(
                        if (targetRepository == null)
                            Resource.error("", emptyList())
                        else
                            Resource.success(targetRepository.products)
                    )
                }
            }
        }.getAsObservable()
    }
}
