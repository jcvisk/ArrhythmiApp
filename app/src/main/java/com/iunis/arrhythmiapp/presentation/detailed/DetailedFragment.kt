package com.iunis.arrhythmiapp.presentation.detailed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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

        binding.tvTest.text = args.itemHeartData.fecha.toString()
    }
}