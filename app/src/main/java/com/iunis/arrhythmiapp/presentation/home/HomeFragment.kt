package com.iunis.arrhythmiapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    private fun initListComponent() {
        binding.rvHeartData.apply {
            adapter = heartDataListAdapter
        }
    }

    private fun initListeners() {
        with(binding) {
            bAddHeartData.setOnClickListener { showAddHeartDataDialog() }
            heartDataListAdapter.setHeartDataClickListener { showDeleteHeartDataDialog(it) }
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
                date = it.date
            ))
        }
        addHeartDataDialog.show(parentFragmentManager, "add_note_dialog")
    }

    private fun showDeleteHeartDataDialog(heartData: HeartData) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailedFragment(itemHeartData = heartData)
        findNavController().navigate(action)
        /*val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Eliminar datos")
        alertDialog.setMessage("Se eliminarán estos datos definitivamente")

        alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deleteHeartData(heartData)
        }

        *//*alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }*//*
        alertDialog.show()*/
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}