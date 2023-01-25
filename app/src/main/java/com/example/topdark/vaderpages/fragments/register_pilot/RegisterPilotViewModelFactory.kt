package com.example.topdark.vaderpages.fragments.register_pilot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.vaderpages.fragments.register_mission.data.RegisterMissionDataSource
import com.example.topdark.vaderpages.fragments.register_pilot.data.RegisterPilotDataSource

class RegisterPilotViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterPilotViewModel::class.java)) {
            return RegisterPilotViewModel(
                registerPilotDataSource = RegisterPilotDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}