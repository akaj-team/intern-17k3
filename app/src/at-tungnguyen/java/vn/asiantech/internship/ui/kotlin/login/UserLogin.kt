package vn.asiantech.internship.ui.kotlin.login

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 23/02/2018.
 */
@Entity(tableName = "user")
data class UserLogin(var email: String = "", var password:String = "" ) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0

}