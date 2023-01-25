package com.example.topdark.vaderpages.fragments.ranking_pilots

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.databinding.FragmentRakingPilotsBinding
import com.example.topdark.vaderpages.fragments.ranking_pilots.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Globals
import models.data_classes.to_present.Pilot

class RakingPilots : Fragment() {
    private var _binding: FragmentRakingPilotsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecyclerView : RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRakingPilotsBinding.inflate(inflater, container, false)

        var pilots: MutableList<Pilot>? = null
        lateinit var miAdapter: Adapter
        CoroutineScope(Dispatchers.Main).launch{
            pilots= Globals.userServices.getAllPilots()
        }.invokeOnCompletion {
            miRecyclerView = binding.RVRankingPilots
            miRecyclerView.setHasFixedSize(true)
            miRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            miAdapter = Adapter(pilots!!, requireContext())
            miRecyclerView.adapter = miAdapter
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
}