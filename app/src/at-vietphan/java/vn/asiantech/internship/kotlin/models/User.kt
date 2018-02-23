package vn.asiantech.internship.kotlin.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 22/02/2018.
 */
@Entity(tableName = "user")
data class User(var userName: String = "", var password: String = "", var email: String = "") {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
//    @ColumnInfo(name = "user_name")
//    var mUserName: String = ""
//    @ColumnInfo(name = "password")
//    var mPassword = ""
//    @ColumnInfo(name = "email")
//    var mEmail = ""
}
