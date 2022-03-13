package com.neppplus.keepthetime_weekend_20220312.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.neppplus.keepthetime_weekend_20220312.fragments.AppointmentListFragment
import com.neppplus.keepthetime_weekend_20220312.fragments.MyProfileFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> AppointmentListFragment()
            else -> MyProfileFragment()
        }

    }
}