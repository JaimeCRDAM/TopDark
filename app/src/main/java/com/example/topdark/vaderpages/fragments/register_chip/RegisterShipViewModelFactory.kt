package com.example.topdark.vaderpages.fragments.register_chip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.vaderpages.fragments.register_chip.data.RegisterShipDataSource
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.data.RegisterPilotToSource

class RegisterShipViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterShipViewModel::class.java)) {
            return RegisterShipViewModel(
                registerShipDataSource = RegisterShipDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}