package com.example.topdark.vaderpages.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.topdark.R
import com.example.topdark.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BTNRegisterPilot.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_registerPilot)
        }

        binding.BTNRegisterPilotToMission.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_registerPilotToMission)
        }

        binding.BTNPilotRanking.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_rakingPilots)
        }

        binding.BTNRegisterShip.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_registerShip)
        }

        binding.BTNRegisterMission.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_registerMission)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}