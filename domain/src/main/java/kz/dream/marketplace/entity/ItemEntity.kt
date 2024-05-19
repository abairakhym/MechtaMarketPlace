package kz.dream.marketplace.entity

import android.os.Parcel
import android.os.Parcelable

data class ItemEntity(
    val id: Int,
    val name: String,
    val photos: List<String>,
    val title: String,
    var isFavorite: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: listOf(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeStringList(photos)
        parcel.writeString(title)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemEntity> {
        override fun createFromParcel(parcel: Parcel): ItemEntity {
            return ItemEntity(parcel)
        }

        override fun newArray(size: Int): Array<ItemEntity?> {
            return arrayOfNulls(size)
        }
    }
}