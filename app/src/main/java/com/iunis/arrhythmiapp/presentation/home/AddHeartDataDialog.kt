package com.iunis.arrhythmiapp.presentation.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.iunis.arrhythmiapp.databinding.DialogAddHeartDataBinding
import com.iunis.arrhythmiapp.domain.model.HeartData
import java.time.Instant

class AddHeartDataDialog() : DialogFragment() {

    private lateinit var binding : DialogAddHeartDataBinding
    private var onSubmitClickListener: ((HeartData) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddHeartDataBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bAddHeartData.setOnClickListener {
            onSubmitClickListener?.invoke(
                HeartData(
                    systolic = binding.etSystolic.text.toString().toInt(),
                    diastolic = binding.etDiastolic.text.toString().toInt(),
                    pulse = binding.etPulse.text.toString().toInt(),
                    note = binding.etNote.text.toString(),
                    date = TextUtils.substring(Instant.now().toString(),0,10)
                )
            )
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun setOnAddNoteClickListener(action: (HeartData) -> Unit) {
        onSubmitClickListener = action
    }
}