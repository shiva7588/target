package com.target.targetcasestudy.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.target.targetcasestudy.database.entity.DealEntity

@Dao
interface DealsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDealEntries(deals: List<DealEntity>)

    @Query("select * from `DealEntity`")
    fun getAllDeals() : List<DealEntity>
}