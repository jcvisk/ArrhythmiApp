package com.iunis.arrhythmiapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.iunis.arrhythmiapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val args: HomeFragmentArgs by navArgs()

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

        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            bAddNote.setOnClickListener { showAddNoteDialog() }

        }
    }

    private fun showAddNoteDialog() {
        val addHeartDataDialog = AddHeartDataDialog()
        addHeartDataDialog.setOnAddNoteClickListener {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        addHeartDataDialog.show(parentFragmentManager, "add_note_dialog")
    }

    private fun showDeleteNoteDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Eliminar nota")
        alertDialog.setMessage("Se eliminará  esta nota definitivamente")

        alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(requireContext(), android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        alertDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}