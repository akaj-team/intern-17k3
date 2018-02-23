package vn.asiantech.internship.kotlin.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import vn.asiantech.internship.kotlin.models.User

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 22/02/2018.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    fun findUserByEmailAndPassword(email: String, password: String): User
}
