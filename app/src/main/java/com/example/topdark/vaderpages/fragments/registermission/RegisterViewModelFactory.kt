package com.example.topdark.vaderpages.fragments.registermission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.data.LoginDataSource
import com.example.topdark.data.LoginRepository
import com.example.topdark.ui.login.LoginViewModel

class RegisterViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}