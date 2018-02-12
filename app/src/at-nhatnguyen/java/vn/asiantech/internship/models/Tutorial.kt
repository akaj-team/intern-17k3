package vn.asiantech.internship.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hoangnhat on 02/02/2018.
 * Tutorial
 */
data class Tutorial(var color: Int, var imageHeader: Int, var title: String, var content: String, var imageViewClick: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(color)
        parcel.writeInt(imageHeader)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeInt(imageViewClick)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tutorial> {
        override fun createFromParcel(parcel: Parcel): Tutorial {
            return Tutorial(parcel)
        }

        override fun newArray(size: Int): Array<Tutorial?> {
            return arrayOfNulls(size)
        }
    }
}
