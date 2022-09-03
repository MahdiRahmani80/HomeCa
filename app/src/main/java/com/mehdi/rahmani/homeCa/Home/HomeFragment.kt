package com.mehdi.rahmani.homeCa.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mehdi.rahmani.homeCa.Home.ViewPager.HomeViewPagerAdapter
import com.mehdi.rahmani.homeCa.Model.Objects.City
import com.mehdi.rahmani.homeCa.Model.Objects.NeighbourInCity
import com.mehdi.rahmani.homeCa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // make view model
        val vm: HomeViewModel by viewModels()

        vm.getNeighbours().observe(requireActivity(), Observer<List<City>> { data ->
            binding.pagerHome.adapter = HomeViewPagerAdapter(requireActivity(), data)
            TabLayoutMediator(binding.tabLayout,binding.pagerHome){tab,position ->
                tab.text = data[position].city_name
            }.attach()
        })





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}