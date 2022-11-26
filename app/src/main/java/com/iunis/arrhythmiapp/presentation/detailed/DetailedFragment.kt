package com.iunis.arrhythmiapp.presentation.detailed

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ekn.gruzer.gaugelibrary.Range
import com.iunis.arrhythmiapp.R
import com.iunis.arrhythmiapp.databinding.FragmentDetailedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedFragment : Fragment() {

    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!

    private val args: DetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPanel()
        initGraficas()

        binding.bBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
    private fun initPanel(){
        binding.sistolica.text = args.itemHeartData.systolic.toString()
        binding.diastolica.text = args.itemHeartData.diastolic.toString()
        binding.pulso.text = args.itemHeartData.pulse.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun initGraficas(){

        //Asignando color y rangos
        binding.hgSystolic.addRange(configureRanges(80.0, 90.0, "#E74C3C"))
        binding.hgSystolic.addRange(configureRanges(90.0, 100.0, "#F1C40F"))
        binding.hgSystolic.addRange(configureRanges(100.0, 120.0, "#2ECC71"))
        binding.hgSystolic.addRange(configureRanges(120.0, 130.0, "#F1C40F"))
        binding.hgSystolic.addRange(configureRanges(130.0, 140.0, "#E74C3C"))
        //Asignando color y rangos
        binding.hgDiastolic.addRange(configureRanges(60.0, 70.0, "#E74C3C"))
        binding.hgDiastolic.addRange(configureRanges(70.0, 80.0, "#F1C40F"))
        binding.hgDiastolic.addRange(configureRanges(80.0, 90.0, "#2ECC71"))
        binding.hgDiastolic.addRange(configureRanges(90.0, 100.0, "#F1C40F"))
        binding.hgDiastolic.addRange(configureRanges(100.0, 110.0, "#E74C3C"))
        //Asignando color y rangos
        binding.hgPulse.addRange(configureRanges(40.0, 50.0, "#E74C3C"))
        binding.hgPulse.addRange(configureRanges(50.0, 60.0, "#F1C40F"))
        binding.hgPulse.addRange(configureRanges(60.0, 100.0, "#2ECC71"))
        binding.hgPulse.addRange(configureRanges(100.0, 110.0, "#F1C40F"))
        binding.hgPulse.addRange(configureRanges(110.0, 120.0, "#E74C3C"))

        //Asignando min max y valor actuala
        binding.hgSystolic.minValue = 80.0
        binding.hgSystolic.maxValue = 140.0
        binding.hgSystolic.value = args.itemHeartData.systolic!!.toDouble()

        //Asignando min max y valor actual
        binding.hgDiastolic.minValue = 60.0
        binding.hgDiastolic.maxValue = 110.0
        binding.hgDiastolic.value = args.itemHeartData.diastolic!!.toDouble()

        //Asignando min max y valor actual
        binding.hgPulse.minValue = 40.0
        binding.hgPulse.maxValue = 120.0
        binding.hgPulse.value = args.itemHeartData.pulse!!.toDouble()

        binding.tvNota.text = "Nota: ${args.itemHeartData.note}"
    }

    private fun configureRanges(from:Double, to:Double, color: String): Range{
        val range = Range()
        range.from = from
        range.to = to
        range.color = Color.parseColor(color)
        return range
    }
}