package com.mehdi.rahmani.homeCa.Home.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mehdi.rahmani.homeCa.Model.Objects.City

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity,private val data: List<City>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return HomeChartFragment(position)
    }

}