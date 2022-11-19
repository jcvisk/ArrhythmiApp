package com.iunis.arrhythmiapp.presentation.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.iunis.arrhythmiapp.databinding.DialogAddHeartDataBinding

class AddHeartDataDialog() : DialogFragment() {
    private lateinit var binding : DialogAddHeartDataBinding
    private var onSubmitClickListener: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddHeartDataBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bAddNote.setOnClickListener {
            //onSubmitClickListener?.invoke(binding.etNote.text.toString())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun setOnAddNoteClickListener(action: (String) -> Unit) {
        onSubmitClickListener = action
    }
}