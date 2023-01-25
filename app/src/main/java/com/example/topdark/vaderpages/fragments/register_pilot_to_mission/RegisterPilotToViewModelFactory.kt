package com.example.topdark.vaderpages.fragments.register_pilot_to_mission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.data.RegisterPilotToSource

class RegisterPilotToViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterPilotToViewModel::class.java)) {
            return RegisterPilotToViewModel(
                registerShipDataSource = RegisterPilotToSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}