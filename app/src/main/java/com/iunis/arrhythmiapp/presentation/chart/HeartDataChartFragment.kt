package com.iunis.arrhythmiapp.presentation.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.iunis.arrhythmiapp.databinding.FragmentHeartDataChartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeartDataChartFragment : Fragment() {

    lateinit var lineChart: LineChart

    private var _binding: FragmentHeartDataChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHeartDataChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineChart = binding.lChart

        val list:ArrayList<Entry> = ArrayList()
        list.add(Entry(100f, 100f))
        list.add(Entry(101f, 101f))
        list.add(Entry(102f, 101.5f))
        list.add(Entry(103f, 103f))
        list.add(Entry(104f, 102.5f))

        val lineDataSet = LineDataSet(list,"Sistolica")

        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)

        lineDataSet.valueTextColor=Color.BLACK


        val list2:ArrayList<Entry> = ArrayList()
        list2.add(Entry(100f, 100f))
        list2.add(Entry(101f, 102f))
        list2.add(Entry(102f, 101f))
        list2.add(Entry(103f, 103.5f))
        list2.add(Entry(104f, 102f))

        val lineDataSet2 = LineDataSet(list2,"diastolica")

        lineDataSet2.setColors(ColorTemplate.MATERIAL_COLORS, 255)

        lineDataSet2.valueTextColor=Color.BLACK




        val lineData = LineData(lineDataSet, lineDataSet2)

        lineChart.data=lineData
        lineChart.description.text="Line Chart"
        lineChart.animateY(2000)

    }

}