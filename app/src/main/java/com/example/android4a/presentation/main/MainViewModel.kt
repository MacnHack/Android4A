package com.example.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.useCase.CreateUserUseCase
import com.example.android4a.domain.useCase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel (
        private val createUserUseCase: CreateUserUseCase,
        private val getUserUseCase: GetUserUseCase
): ViewModel(){

    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 0
    }

    fun onClickedIncrement(emailUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(User("test"))
            delay(1000)
            val user = getUserUseCase.invoke("test")
        }
        //counter.value = (counter.value ?: 0) + 1
    }
}