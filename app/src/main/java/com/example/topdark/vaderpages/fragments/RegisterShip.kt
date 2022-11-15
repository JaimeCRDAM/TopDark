package com.example.topdark.vaderpages.fragments

import Models.dataclasses.ship.ShipsEnum
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.topdark.databinding.FragmentRegisterShipBinding


class RegisterShip : Fragment() {
    private var _binding: FragmentRegisterShipBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private val galleryResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                imageUri = it.data?.data
                binding.IVShip.setImageURI(imageUri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterShipBinding.inflate(inflater, container, false)
        val shipArray = ShipsEnum.values().map {it.shipName}
        val adapter = ArrayAdapter(
            this.requireContext(), android.R.layout.simple_spinner_dropdown_item, shipArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerShip.adapter = adapter

        binding.SpinnerShip.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.selectedItem.toString()
                setRadioOn()
                if(!ShipsEnum.valueOf(item).cargo!!) {
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
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun setRadioOn(){
        binding.RBCarga.isEnabled = true
        binding.RBPassengers.isEnabled = true
    }

    fun setRadioOff(){
        binding.RBCarga.isEnabled = !binding.RBCarga.isEnabled
        binding.RBPassengers.isEnabled = !binding.RBPassengers.isEnabled
    }

    fun openImageChooser() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryResult.launch(gallery)
    }
}