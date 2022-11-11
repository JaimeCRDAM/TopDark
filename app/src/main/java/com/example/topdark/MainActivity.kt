package com.example.topdark

import Models.Globals
import Models.dataclasses.UserCredentials
import Requests.ServerRequests
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.topdark.databinding.ActivityMainBinding
import httpmethods.RetrofitClientImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Base64

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTNLogin.setOnClickListener {
            val userCredentials = UserCredentials(binding.TVLogin.text.toString(), binding.TVPassword.text.toString())
            Globals.UserServices.logInWithCredentials(userCredentials)
        }
    }


}