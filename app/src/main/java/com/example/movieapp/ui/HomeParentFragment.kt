package com.example.movieapp.ui

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.databinding.FragmentHomeParentBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeParentFragment :
    BaseFragment<FragmentHomeParentBinding>(FragmentHomeParentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setListeners()
    }


    private fun setUpViewPager() {
        binding.apply {
            viewPager.adapter = HomeParentAdapter(requireActivity())
            tabLayout.apply {
                this.addTab(this.newTab().setText("Animation Fragment"))
                this.addTab(this.newTab().setText("Movies Fragment"))
            }
        }
    }

    private fun setListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}