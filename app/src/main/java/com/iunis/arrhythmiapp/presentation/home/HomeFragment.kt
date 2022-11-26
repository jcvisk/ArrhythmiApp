package com.iunis.arrhythmiapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.HEART_DATA_COLLECTION
import com.iunis.arrhythmiapp.databinding.FragmentHomeBinding
import com.iunis.arrhythmiapp.domain.model.HeartData
import com.iunis.arrhythmiapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val heartDataListAdapter: HeartDataListAdapter = HeartDataListAdapter()

    private lateinit var firestoreListener: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListComponent()
        initListeners()
        initObservers()

        getHeartData()
        confiPrincipalPanel()
    }

    private fun initObservers() {
        viewModel.addHeartDataState.observe(viewLifecycleOwner){ state ->
            when(state){
                is Resource.Success -> Unit
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
        viewModel.deleteHeartDataState.observe(viewLifecycleOwner){ state ->
            when(state){
                is Resource.Success -> Unit
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
        viewModel.heartDataListState.observe(viewLifecycleOwner){ state ->
            when(state){
                is Resource.Success -> heartDataListAdapter.submitList(state.data)
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
    }

    private fun getHeartData() {
        viewModel.getAllHeartData()
    }

    private fun confiPrincipalPanel() {
        val db = FirebaseFirestore.getInstance()
        val heartDataRef = db.collection(HEART_DATA_COLLECTION).orderBy("fecha", Query.Direction.DESCENDING).limit(1)

        firestoreListener = heartDataRef.addSnapshotListener { snapshots, error ->

            if(error!=null){
                Toast.makeText(requireContext(),"Error al consultar datos", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            for(snapshots  in snapshots!!.documentChanges){
                val heartData = snapshots.document.toObject(HeartData::class.java)

                when(snapshots.type){
                    DocumentChange.Type.ADDED ->{
                        binding.sistolica.text = heartData.systolic.toString()
                        binding.diastolica.text = heartData.diastolic.toString()
                        binding.pulso.text = heartData.pulse.toString()
                    }
                    DocumentChange.Type.MODIFIED ->{
                        binding.sistolica.text = heartData.systolic.toString()
                        binding.diastolica.text = heartData.diastolic.toString()
                        binding.pulso.text = heartData.pulse.toString()
                    }
                    DocumentChange.Type.REMOVED ->{
                        binding.sistolica.text = heartData.systolic.toString()
                        binding.diastolica.text = heartData.diastolic.toString()
                        binding.pulso.text = heartData.pulse.toString()
                    }
                }
            }
        }
    }

    private fun initListComponent() {
        binding.rvHeartData.apply {
            adapter = heartDataListAdapter
        }
    }

    private fun initListeners() {
        with(binding) {
            bAddHeartData.setOnClickListener {
                showAddHeartDataDialog()
            }
            heartDataListAdapter.setHeartDataClickListener {
                goToDetailedHeartDataFragment(it)
            }
            heartDataListAdapter.setHeartDataLongClickListener {
                showDeleteHeartDataDialog(it)
            }
            bShowChart.setOnClickListener {
                goToHeartDataChartFragment()
            }
        }
    }

    private fun showAddHeartDataDialog() {
        val addHeartDataDialog = AddHeartDataDialog()
        addHeartDataDialog.setOnAddNoteClickListener {
            viewModel.saveHeartData(HeartData(
                systolic = it.systolic,
                diastolic = it.diastolic,
                pulse = it.pulse,
                note = it.note,
                fecha = it.fecha
            ))
        }
        addHeartDataDialog.show(parentFragmentManager, "add_note_dialog")
    }

    private fun showDeleteHeartDataDialog(heartData: HeartData) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Eliminar datos")
        alertDialog.setMessage("Se eliminarán estos datos definitivamente")

        alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deleteHeartData(heartData)
        }

        /*alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }*/
        alertDialog.show()
    }

    private fun goToDetailedHeartDataFragment(heartData: HeartData) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailedFragment(itemHeartData = heartData)
        findNavController().navigate(action)
    }

    private fun goToHeartDataChartFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToHeartDataChartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}