package com.mehdi.rahmani.homeCa.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.mehdi.rahmani.homeCa.R
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import com.mehdi.rahmani.homeCa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add tab
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("تهران"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("تبریز"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ارومیه"))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}