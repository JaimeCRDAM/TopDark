package com.example.topdark.vaderpages.fragments.register_mission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.vaderpages.fragments.register_mission.data.RegisterMissionDataSource

class RegisterViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                regiserMissionDataSource = RegisterMissionDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}