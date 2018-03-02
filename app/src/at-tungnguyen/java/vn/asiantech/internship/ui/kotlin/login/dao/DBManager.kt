package vn.asiantech.internship.ui.kotlin.login.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import vn.asiantech.internship.ui.kotlin.login.UserLogin

/**
 *
 * Author Asian Tech Inc.
 * Created by tungnguyen on 23/02/2018.
 */
@Database(entities = arrayOf(UserLogin::class), version = 1)
abstract class DBManager : RoomDatabase() {
    abstract fun userDao():UserDao
}