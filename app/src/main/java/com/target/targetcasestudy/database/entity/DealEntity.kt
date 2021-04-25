package com.target.targetcasestudy.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class DealEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("aisle")
    val aisle: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("regular_price")
    val regularPrice: PriceEntity?,

    @SerializedName("sale_price")
    val salePrice: PriceEntity?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(PriceEntity::class.java.classLoader),
        parcel.readParcelable(PriceEntity::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(aisle)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeParcelable(regularPrice, flags)
        parcel.writeParcelable(salePrice, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DealEntity> {
        override fun createFromParcel(parcel: Parcel): DealEntity {
            return DealEntity(parcel)
        }

        override fun newArray(size: Int): Array<DealEntity?> {
            return arrayOfNulls(size)
        }
    }
}
