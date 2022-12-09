package com.iunis.arrhythmiapp.presentation.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.iunis.arrhythmiapp.data.utils.FirebaseConstants.HEART_DATA_COLLECTION
import com.iunis.arrhythmiapp.databinding.FragmentHeartDataChartBinding
import com.iunis.arrhythmiapp.domain.model.HeartData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeartDataChartFragment : Fragment() {

    lateinit var lineChart: LineChart

    private var _binding: FragmentHeartDataChartBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestoreListener: ListenerRegistration

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

        configFirestore()

    }


    private fun configFirestore(){
        val db = FirebaseFirestore.getInstance()
        db.collection(HEART_DATA_COLLECTION).orderBy("fecha", Query.Direction.DESCENDING).limit(10)
            .get()
            .addOnSuccessListener { snapshots ->

                lineChart = binding.lChart

                val systolicList:ArrayList<Entry> = ArrayList()
                val diastolicList:ArrayList<Entry> = ArrayList()
                val pulseList:ArrayList<Entry> = ArrayList()

                var distancia = 1f
                for(document in snapshots){
                    val heartData = document.toObject(HeartData::class.java)
                    systolicList.add(Entry(distancia++, heartData.systolic!!.toFloat()))
                    diastolicList.add(Entry(distancia++, heartData.diastolic!!.toFloat()))
                    pulseList.add(Entry(distancia++, heartData.pulse!!.toFloat()))
                }

                val systolicLineDataSet = LineDataSet(systolicList,"Sistólica")
                systolicLineDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
                systolicLineDataSet.valueTextColor=Color.BLACK

                val diastolicLineDataSet = LineDataSet(diastolicList,"Diastólica")
                diastolicLineDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
                diastolicLineDataSet.valueTextColor=Color.BLACK

                val pulseLineDataSet = LineDataSet(pulseList,"Pulso")
                pulseLineDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
                pulseLineDataSet.valueTextColor=Color.BLACK




                val lineData = LineData(systolicLineDataSet, diastolicLineDataSet, pulseLineDataSet)

                lineChart.data=lineData
                lineChart.description.text="Line Chart"
                lineChart.animateY(2000)

            }
            .addOnFailureListener{
                Toast.makeText(requireContext(),"Error al consultar datos", Toast.LENGTH_SHORT).show()
            }
    }
}