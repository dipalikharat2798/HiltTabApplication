package com.geico.hilttabapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.geico.hilttabapplication.R
import com.geico.hilttabapplication.databinding.FragmentHomeBinding
import com.geico.tabmvvmapp.adapter.MY_GARDEN_PAGE_INDEX
import com.geico.tabmvvmapp.adapter.PLANT_LIST_PAGE_INDEX
import com.geico.tabmvvmapp.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = PagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.ic_baseline_person_24
            PLANT_LIST_PAGE_INDEX -> R.drawable.ic_baseline_computer_24
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.firstTab)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.secondTab)
            else -> null
        }
    }
}