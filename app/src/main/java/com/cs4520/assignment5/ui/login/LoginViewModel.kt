package com.cs4520.assignment5.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment5.model.User

class LoginViewModel: ViewModel() {

    var user = MutableLiveData<User>()

    fun setUser(usrName: String, password: String) {
        val newUser = User(usrName, password)
        user.value = newUser
    }

    fun isUserValid(user:User?): Boolean {
        return if (user != null) {
            user.userName == "admin" && user.password == "admin"
        } else {
            false
        }
    }

}