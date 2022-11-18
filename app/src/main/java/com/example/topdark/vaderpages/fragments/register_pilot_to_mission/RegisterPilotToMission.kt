package com.example.topdark.vaderpages.fragments.register_pilot_to_mission

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.R
import com.example.topdark.databinding.FragmentRegisterPilotToMissionBinding
import com.example.topdark.vaderpages.fragments.register_mission.RegisterViewModel
import com.example.topdark.vaderpages.fragments.register_mission.RegisterViewModelFactory
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter.SpinnerAdapterMissions
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter.SpinnerAdapterPilots
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter.SpinnerAdapterShips
import kotlinx.coroutines.*
import models.Globals
import models.data_classes.PilotToMission
import models.data_classes.to_present.Mission
import models.data_classes.to_present.Pilot
import models.data_classes.to_present.Ship

class RegisterPilotToMission : Fragment() {

    private var _binding: FragmentRegisterPilotToMissionBinding? = null

    lateinit var pilotsAdapter: SpinnerAdapterPilots
    lateinit var missionsAdapter: SpinnerAdapterMissions
    lateinit var shipsAdapter: SpinnerAdapterShips
    private lateinit var registerPilotToViewModel: RegisterPilotToViewModel
    private val binding get() = _binding!!
    var pilots: MutableList<Pilot>? = null
    var auxpilots: List<Pilot>? = null
    var missions: MutableList<Mission>? = null
    var auxmissions: List<Mission>? = null
    var ships: MutableList<Ship>? = null
    var auxships: List<Ship>? = null
    var missionPos = 0
    var shipPos = 0
    var pilotPosition = 0



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch{
            joinAll(
                launch{
                    var start = System.currentTimeMillis()
                    pilots = Globals.userServices.getAllPilots()
                    auxpilots = pilots?.toList()
                    pilotsAdapter = SpinnerAdapterPilots(requireContext(), pilots!!)
                    println("pilotos "+(System.currentTimeMillis()-start))
                },
                launch{
                    var start = System.currentTimeMillis()
                    missions = Globals.userServices.getAllMissions()
                    auxmissions = missions?.toList()
                    println("misiones "+(System.currentTimeMillis()-start))

                },
                launch{
                    var start = System.currentTimeMillis()
                    ships = Globals.userServices.getAllShips()
                    auxships = ships?.toList()
                    shipsAdapter = SpinnerAdapterShips(requireContext(), ships!!)
                    println("naves "+(System.currentTimeMillis()-start))

                },

                ).joinAll()

            CoroutineScope(Dispatchers.Main).launch {
                missionsAdapter = SpinnerAdapterMissions(requireContext(), missions!!, ships!!)
                var start = System.currentTimeMillis()
                val pilots = requireView().findViewById<Spinner>(R.id.SpinnerPilotsMissions)
                pilots.adapter = pilotsAdapter
                println("pilotos adapter "+(System.currentTimeMillis()-start))
                start = System.currentTimeMillis()
                val missions = requireView().findViewById<Spinner>(R.id.SpinnerMissionsMissions)
                missions.adapter = missionsAdapter
                println("misiones adapter "+(System.currentTimeMillis()-start))
                val ships = requireView().findViewById<Spinner>(R.id.SpinnerShipsMissions)
                ships.adapter = shipsAdapter
                println("naves adapter "+(System.currentTimeMillis()-start))
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prepareViewModel()
        auxiliarCreateView(inflater, container)
        _binding = FragmentRegisterPilotToMissionBinding.inflate(inflater, container, false)
        val hashmap = hashMapOf<String, String>()
        hashmap["Fighters"] = "Combat"
        hashmap["Shuttles"] = "Flight"
        hashmap["Bombers"] = "Bombardment"
        binding.SpinnerPilotsMissions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    pilotPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.SpinnerMissionsMissions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    missionPos = position
                    ships = auxships?.filter {
                        it.firstCheck == missions?.get(position)?.firstCheck&&
                                it.secondCheck == missions?.get(position)?.secondCheck   &&
                                hashmap[it.type] == missions?.get(position)?.missionType

                    } as MutableList<Ship>
                    binding.SpinnerShipsMissions.visibility = View.GONE
                    binding.SpinnerShipsMissions.adapter = null
                    binding.SpinnerShipsMissions.adapter = SpinnerAdapterShips(requireContext(), ships!!)
                    binding.SpinnerShipsMissions.visibility = View.VISIBLE
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.SpinnerShipsMissions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        shipPos = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.BTNRegisterMissionToPilot.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                val pilotToMission = PilotToMission(
                    auxpilots!![pilotPosition]._id,
                    auxmissions!![missionPos]._id,
                    ships!![shipPos].plate
                )
                registerPilotToViewModel.requestResult(pilotToMission)//.requestResult(pilotToMission)
            }
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

    private fun joinAll(vararg jobs: Job): MutableList<Job>{
        return jobs.toMutableList()
    }

    private fun auxiliarCreateView(
        inflater: LayoutInflater, container: ViewGroup?
    ){

    }
    fun prepareViewModel(){
        registerPilotToViewModel = ViewModelProvider(this, RegisterPilotToViewModelFactory())
            .get(RegisterPilotToViewModel::class.java)

        registerPilotToViewModel.registerPilotToResult.observe(viewLifecycleOwner, Observer {
            val registerPilotToResult = it ?: return@Observer

            if (registerPilotToResult.code != null) {
                Toast.makeText(requireContext(), "registerPilotToResult.message", Toast.LENGTH_SHORT).show()
            }
            if (registerPilotToResult.message != null) {
                Toast.makeText(requireContext(), "registerPilotToResult.message", Toast.LENGTH_SHORT).show()
            }

            //Complete and destroy login activity once successful
            //finish()
        })
    }
}