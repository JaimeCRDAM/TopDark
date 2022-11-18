package com.example.topdark.vaderpages.fragments.register_chip

import models.data_classes.ship.ShipsEnum
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.databinding.FragmentRegisterShipBinding
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToViewModel
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.data_classes.ship.GeneralShipDataClass
import utils.gzip
import java.io.ByteArrayOutputStream
import java.util.*


class RegisterShip : Fragment() {
    private var _binding: FragmentRegisterShipBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    lateinit var item: String
    private val galleryResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                imageUri = it.data?.data
                binding.IVShip.setImageURI(imageUri)
            }
        }

    private lateinit var registerShipViewModel: RegisterShipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterShipBinding.inflate(inflater, container, false)

        prepareViewModel()
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shipArray = ShipsEnum.values().map {it.shipName}
        val adapter = ArrayAdapter(
            this.requireContext(), android.R.layout.simple_spinner_dropdown_item, shipArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerShip.adapter = adapter

        binding.SpinnerShip.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                this@RegisterShip.item = parent.selectedItem.toString()
                setRadioFalse()
                setRadioOn()
                if(!ShipsEnum.valueOf(item).cargo) {
                    setRadioOff()
                }
                val resource = ShipsEnum.valueOf(item).avatar
                binding.IVShip.setImageBitmap(BitmapFactory.decodeResource(resources, resource!!))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.BTNStorage3.setOnClickListener {
            openImageChooser()
        }

        binding.BTNRegisterShipMenu.setOnClickListener {
            val bitmap = binding.IVShip.drawable.toBitmap()
            val bos =  ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
            val bitmapdata = bos.toByteArray()
            val encodeToByteArray = gzip(Base64.getEncoder().encodeToString(bitmapdata))
            val encodeToString = Base64.getEncoder().encodeToString(encodeToByteArray)
            val klass = ShipsEnum.valueOf(item)

            val generalDataClass = GeneralShipDataClass(
                binding.TVPlate.text.toString(),
                klass.shipName,
                encodeToString,
                binding.RBCarga.isChecked,
                binding.RBPassengers.isChecked
            )

            CoroutineScope(Dispatchers.IO).launch{
                registerShipViewModel.requestResult(generalDataClass)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun setRadioOn(){
        binding.RBCarga.isEnabled = true
        binding.RBPassengers.isEnabled = true
    }
    fun setRadioFalse(){
        binding.RBCarga.isChecked = false
        binding.RBPassengers.isChecked = false
    }

    fun setRadioOff(){
        binding.RBCarga.isEnabled = false
        binding.RBPassengers.isEnabled = false
    }

    fun openImageChooser() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryResult.launch(gallery)
    }

    fun prepareViewModel(){
        registerShipViewModel = ViewModelProvider(
            this,
            RegisterShipViewModelFactory()
        )[RegisterShipViewModel::class.java]

        registerShipViewModel.registerShipResult.observe(viewLifecycleOwner, Observer {
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