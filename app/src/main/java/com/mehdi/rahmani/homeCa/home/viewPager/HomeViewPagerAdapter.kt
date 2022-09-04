package com.mehdi.rahmani.homeCa.home.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mehdi.rahmani.homeCa.model.objects.City

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity,private val data: List<City>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        val h = HomeChartFragment()
        h.position =position
        return h
    }

}