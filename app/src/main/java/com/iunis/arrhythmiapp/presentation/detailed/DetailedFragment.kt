package com.iunis.arrhythmiapp.presentation.detailed

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ekn.gruzer.gaugelibrary.Range
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

    private fun initGraficas(){

        //Asignando color y rangos
        binding.hgSystolic.addRange(configureRanges(0.0, 50.0, "#00b20b"))
        binding.hgSystolic.addRange(configureRanges(50.0, 100.0, "#E3E500"))
        binding.hgSystolic.addRange(configureRanges(100.0, 150.0, "#ce0000"))
        //Asignando color y rangos
        binding.hgDiastolic.addRange(configureRanges(0.0, 50.0, "#00b20b"))
        binding.hgDiastolic.addRange(configureRanges(50.0, 100.0, "#E3E500"))
        binding.hgDiastolic.addRange(configureRanges(100.0, 150.0, "#ce0000"))
        //Asignando color y rangos
        binding.hgPulse.addRange(configureRanges(0.0, 50.0, "#00b20b"))
        binding.hgPulse.addRange(configureRanges(50.0, 100.0, "#E3E500"))
        binding.hgPulse.addRange(configureRanges(100.0, 150.0, "#ce0000"))

        //Asignando min max y valor actuala
        binding.hgSystolic.minValue = 0.0
        binding.hgSystolic.maxValue = 150.0
        binding.hgSystolic.value = args.itemHeartData.systolic!!.toDouble()

        //Asignando min max y valor actual
        binding.hgDiastolic.minValue = 0.0
        binding.hgDiastolic.maxValue = 150.0
        binding.hgDiastolic.value = args.itemHeartData.diastolic!!.toDouble()

        //Asignando min max y valor actual
        binding.hgPulse.minValue = 0.0
        binding.hgPulse.maxValue = 150.0
        binding.hgPulse.value = args.itemHeartData.pulse!!.toDouble()
    }

    private fun configureRanges(from:Double, to:Double, color: String): Range{
        val range = Range()
        range.from = from
        range.to = to
        range.color = Color.parseColor(color)
        return range
    }
}