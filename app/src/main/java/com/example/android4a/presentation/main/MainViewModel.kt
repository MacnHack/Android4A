package com.example.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.useCase.CreateUserUseCase
import com.example.android4a.domain.useCase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel (
        private val createUserUseCase: CreateUserUseCase,
        private val getUserUseCase: GetUserUseCase
): ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()


    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userPass = getUserUseCase.invoke(emailUser)
            val loginStatus = if (userPass?.password == password) {
                LoginSuccess(userPass.email)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }

    fun onClickedSignUp(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userOk = getUserUseCase.invoke(emailUser)
            if (userOk?.email == null) {
                createUserUseCase.invoke(User(emailUser, password))
                onClickedLogin(emailUser, password)
            } else {
                withContext(Dispatchers.Main){loginLiveData.value = SignUpError}
            }
        }
    }
}