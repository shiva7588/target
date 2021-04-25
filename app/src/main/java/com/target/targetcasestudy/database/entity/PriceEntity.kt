package com.target.targetcasestudy.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class PriceEntity(
    @SerializedName("amount_in_cents")
    val amount_in_cents: Int,

    @SerializedName("currency_symbol")
    val currency_symbol: String?,

    @SerializedName("display_string")
    val display_string: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(amount_in_cents)
        parcel.writeString(currency_symbol)
        parcel.writeString(display_string)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PriceEntity> {
        override fun createFromParcel(parcel: Parcel): PriceEntity {
            return PriceEntity(parcel)
        }

        override fun newArray(size: Int): Array<PriceEntity?> {
            return arrayOfNulls(size)
        }
    }
}