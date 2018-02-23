package vn.asiantech.internship.kotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import vn.asiantech.internship.kotlin.dao.UserDao
import vn.asiantech.internship.kotlin.models.User

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 22/02/2018.
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
