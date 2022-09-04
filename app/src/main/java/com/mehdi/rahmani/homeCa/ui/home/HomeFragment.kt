package com.mehdi.rahmani.homeCa.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mehdi.rahmani.homeCa.ui.home.viewPager.HomeViewPagerAdapter
import com.mehdi.rahmani.homeCa.databinding.FragmentHomeBinding
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // make view model
        val vm: HomeViewModel by viewModels()

        mkViewPager(vm)
    }


    private fun mkViewPager(vm: HomeViewModel) {

        vm.getCity().observe(requireActivity()) { data ->
            if(_binding != null) {
                binding.pagerHome.adapter = HomeViewPagerAdapter(requireActivity(), data)
                TabLayoutMediator(binding.tabLayout, binding.pagerHome) { tab, position ->
                    tab.text = data[position].city_name
                }.attach()
            }
        }
    }

    override fun onDestroyView() {
        binding.pagerHome.adapter = null
        _binding = null
        super.onDestroyView()
    }

}