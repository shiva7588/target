package com.target.targetcasestudy.webapi

import com.target.targetcasestudy.database.entity.Products
import io.reactivex.Observable
import retrofit2.http.GET

interface DealsApi {
    @GET("mobile_case_study_deals/v1/deals")
    fun getDeals() : Observable<Products>
}