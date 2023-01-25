package com.example.topdark.pilot_pages.list_of_missions

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.databinding.FragmentSecondBinding
import com.example.topdark.pilot_pages.list_of_missions.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Globals
import models.data_classes.idk.Data


class ViewMissionsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecyclerView : RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        var combo: MutableList<Data> = mutableListOf()
        lateinit var miAdapter: Adapter
        CoroutineScope(Dispatchers.Main).launch{
            combo = Globals.userServices.getAlldata(Globals.user!!.id!!)!!
        }.invokeOnCompletion {
            miRecyclerView = binding.RVPilotMissions
            miRecyclerView.setHasFixedSize(true)
            miRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            miAdapter = Adapter(combo, requireContext())
            miRecyclerView.adapter = miAdapter
        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_First2Fragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}