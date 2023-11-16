package com.example.movieapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeParentAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AnimationFragment()
            1 -> MoviesFragment()

            else -> AnimationFragment()
        }
    }
}