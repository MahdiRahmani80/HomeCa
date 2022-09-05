package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mehdi.rahmani.homeCa.databinding.FragmentHomeChartBinding
import org.koin.android.ext.android.inject


class HomeChartFragment() : Fragment() {

    private val viewModel: HomeChartViewModel by inject()
    var position: Int? = null

    private var _binding: FragmentHomeChartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            setChart()
    }


    private fun setChart() {

        viewModel.getHomeInNeighbour().observe(requireActivity()) {

            val lineDataset1 = LineDataSet(it, "قیمت")
            val lineData = LineData(lineDataset1)
            binding.chart.data = lineData
            binding.chart.invalidate()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}