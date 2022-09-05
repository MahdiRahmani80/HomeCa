package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import android.graphics.Color
import android.graphics.Color.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.mehdi.rahmani.homeCa.databinding.FragmentHomeChartBinding
import org.koin.android.ext.android.inject


class HomeChartFragment() : Fragment() {

    private val viewModel: HomeChartViewModel by inject()
    var position: Int? = 0

    private var _binding: FragmentHomeChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChart()
        setBarChart()
        setPieChart()
    }

    private fun setPieChart() {

        viewModel.getPiePrice(position!!).observe(requireActivity()){ pie->

            val pieDataSet = PieDataSet(pie,"محله ها")
            val pirData = PieData(pieDataSet)

            if(_binding !=null){
                binding.chart4.data = pirData
                binding.chart4.invalidate()
            }
        }

    }

    private fun setBarChart() {

        viewModel.getBarPrice(position!!).observe(requireActivity()){ barData->
            val barDataSet1 = BarDataSet(barData,"قیمت")
            val barData = BarData(barDataSet1)

            if(_binding != null){
                binding.chart3.data = barData
                binding.chart3.invalidate()
            }
        }
    }


    private fun setChart() {

        lateinit var lineDataset1: LineDataSet
        lateinit var lineDataset2: LineDataSet

        // Chart1
        viewModel.getHomePrice(position!!).observe(requireActivity()) { price ->
            lineDataset1 = LineDataSet(price, "قیمت")
            val lineData = LineData(lineDataset1)
            lineDataset1.color = BLACK

            if (_binding != null) {
                binding.chart1.data = lineData
                binding.chart1.invalidate()
            }


            // Chart2
            if (_binding != null)
                viewModel.getHomeArea(position!!).observe(requireActivity()) { area ->
                    lineDataset2 = LineDataSet(area, "مساحت (متر مربع)")

                    val dataSets = ArrayList<ILineDataSet>()

                    if (_binding != null) {
                        dataSets.add(lineDataset1)
                        dataSets.add(lineDataset2)
                        lineDataset2.color = RED

                        binding.chart2.data = LineData(dataSets)
                        binding.chart2.invalidate()
                    }
                } // end view model observer 2
        } // end view model observer 1


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}