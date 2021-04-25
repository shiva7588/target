package com.target.targetcasestudy.database.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PriceEntityTypeConverter {

    @TypeConverter
    fun fromString(jsonString: String?): PriceEntity? {
        val listType: Type = object : TypeToken<PriceEntity?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun fromPriceEntity(price: PriceEntity?): String? {
        val gson = Gson()
        return gson.toJson(price)
    }
}