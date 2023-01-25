package com.example.topdark.vaderpages.fragments.register_pilot

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.databinding.FragmentRegisterPilotBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.data_classes.pilot_register.RegisterPilotData
import utils.gzip
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.zip.Deflater

class RegisterPilot : Fragment() {


    private var _binding: FragmentRegisterPilotBinding? = null
    lateinit var imageBitmap: Bitmap
    private var imageUri: Uri? = null
    private val binding get() = _binding!!
    private val REQUEST_IMAGE_CAPTURE = 1888
    private lateinit var registerPilotViewModel: RegisterPilotViewModel
    private val cameraPermsResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            launchChamera()
        }
    }
    private val galleryResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK){
                imageUri = it.data?.data
                binding.IVAvatar.setImageURI(imageUri)
            }
        }
    private val cameraResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK){
                imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.IVAvatar.setImageBitmap(imageBitmap)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterPilotBinding.inflate(inflater, container, false)
        prepareViewModel()
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BTNCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_IMAGE_CAPTURE)
                val cameraPerms = Intent(Manifest.permission.ACCESS_FINE_LOCATION)
                cameraPermsResult.launch(cameraPerms.toString())
            } else {
                launchChamera()
            }

        }

        binding.BTNStorage.setOnClickListener {
            openImageChooser()
        }

        binding.BTNRegisterPilotMenu.setOnClickListener {
            val bitmap = binding.IVAvatar.drawable.toBitmap()
            val bos =  ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
            val bitmapdata = bos.toByteArray()
            val encodeToByteArray = gzip(Base64.getEncoder().encodeToString(bitmapdata))
            val encodeToString = Base64.getEncoder().encodeToString(encodeToByteArray)
            val pilotData = RegisterPilotData(
                binding.TVUser.text.toString(),
                binding.TVPassword.text.toString(),
                binding.TVUser.text.toString(),
                encodeToString,
                Integer.parseInt(binding.TVExperience.text.toString()),
                Integer.parseInt(binding.TVAge.text.toString())
            )

            CoroutineScope(Dispatchers.IO).launch{
                registerPilotViewModel.requestResult(pilotData)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openImageChooser() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryResult.launch(gallery)
    }

    fun launchChamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        cameraResult.launch(cameraIntent)
    }

    fun prepareViewModel(){
        registerPilotViewModel = ViewModelProvider(this, RegisterPilotViewModelFactory())
            .get(RegisterPilotViewModel::class.java)

        registerPilotViewModel.registerResult.observe(viewLifecycleOwner, Observer {
            val registerResult = it ?: return@Observer

            if (registerResult.code != null) {
                Toast.makeText(requireContext(), registerResult.message.plus(" ").plus(registerResult.code), Toast.LENGTH_SHORT).show()
            }
            if (registerResult.message != null) {
                Toast.makeText(requireContext(), registerResult.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
