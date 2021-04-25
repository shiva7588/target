package com.target.targetcasestudy.database.entity

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    val products : List<DealEntity>
)