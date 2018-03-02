package vn.asiantech.internship.ui.kotlin

import android.app.Application
import android.arch.persistence.room.Room
import vn.asiantech.internship.ui.kotlin.login.dao.DBManager

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 23/02/2018.
 */
class KotlinApplication : Application() {
    companion object {
        var database: DBManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, DBManager::class.java,"app-database").build()
    }
}
