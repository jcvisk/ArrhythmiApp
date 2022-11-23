package com.iunis.arrhythmiapp.presentation.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.firebase.Timestamp
import com.iunis.arrhythmiapp.databinding.DialogAddHeartDataBinding
import com.iunis.arrhythmiapp.domain.model.HeartData

class AddHeartDataDialog() : DialogFragment() {

    private lateinit var binding : DialogAddHeartDataBinding
    private var onSubmitClickListener: ((HeartData) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddHeartDataBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bAddHeartData.setOnClickListener {
            //Validating that the data is not empty
            if (binding.etSystolic.text.toString().isNotEmpty() && binding.etDiastolic.text.toString().isNotEmpty() && binding.etPulse.text.toString().isNotEmpty()){
                onSubmitClickListener?.invoke(
                    HeartData(
                        systolic = binding.etSystolic.text.toString().toInt(),
                        diastolic = binding.etDiastolic.text.toString().toInt(),
                        pulse = binding.etPulse.text.toString().toInt(),
                        note = binding.etNote.text.toString(),
                        fecha = Timestamp.now()
                    )
                )
                dismiss()
            }
            else{
                Toast.makeText(requireContext(),"Los datos no pueden estra vacíos", Toast.LENGTH_SHORT).show()
            }
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun setOnAddNoteClickListener(action: (HeartData) -> Unit) {
        onSubmitClickListener = action
    }
}