package vn.asiantech.internship.ui.kotlin.login.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import vn.asiantech.internship.ui.kotlin.login.UserLogin

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 23/02/2018.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): MutableList<UserLogin>

    @Insert
    fun insertUser(userLogin: UserLogin)

    @Query("SELECT * FROM user Where email LIKE :arg0 AND password LIKE :arg1")
    fun checkUser(arg0: String, arg1: String): UserLogin
}
