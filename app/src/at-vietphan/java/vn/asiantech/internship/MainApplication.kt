package vn.asiantech.internship

import android.app.Application
import android.arch.persistence.room.Room
import vn.asiantech.internship.kotlin.database.UserDatabase

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 22/02/2018.
 */
class MainApplication : Application(){

    companion object {
        lateinit var database: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "userDB").build()
    }
}