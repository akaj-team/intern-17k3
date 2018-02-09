package vn.asiantech.internship.kotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * Created by anh.quach on 2/5/18.
 */
@Parcelize
data class Tutorial(var title: String, var content: String,
                    var backgroundColors: Int, var image: Int, var button: Int) : Parcelable
