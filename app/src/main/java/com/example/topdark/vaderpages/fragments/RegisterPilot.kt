package com.example.topdark.vaderpages.fragments

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.topdark.R
import com.example.topdark.databinding.FragmentRegisterPilotBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterPilot.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterPilot : Fragment() {


    private var _binding: FragmentRegisterPilotBinding? = null
    lateinit var imageBitmap: Bitmap
    private var imageUri: Uri? = null
    private val binding get() = _binding!!
    private val REQUEST_IMAGE_CAPTURE = 1888
    private val SELECT_PICTURE = 1
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
            if(it.resultCode == Activity.RESULT_OK){
                imageUri = it.data?.data
                binding.IVAvatar.setImageURI(imageUri)
            }
        }
    private val cameraResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.IVAvatar.setImageBitmap(imageBitmap)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterPilotBinding.inflate(inflater, container, false)
        return binding.root

    }

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

        binding.BTNBack.setOnClickListener {
            findNavController().navigate(com.example.topdark.R.id.action_registerPilot_to_FirstFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == 100) {

        }
    }

    fun openImageChooser() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryResult.launch(gallery)
    }

    fun launchChamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        cameraResult.launch(cameraIntent)
    }
}
