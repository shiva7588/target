package com.target.targetcasestudy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.target.targetcasestudy.database.dao.DealsDAO
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.database.entity.PriceEntityTypeConverter

@Database(entities = [DealEntity::class], version = 1, exportSchema = false)
@TypeConverters(PriceEntityTypeConverter::class)
abstract class TargetDB : RoomDatabase() {
    abstract fun dealsDao(): DealsDAO
}