package com.rakapermanaputra.popcorn.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {

    val fragmentList = arrayListOf<androidx.fragment.app.Fragment>()
    val titleFragment = arrayListOf<String>()

    fun populateFragment(fragment: androidx.fragment.app.Fragment, title: String) {
        fragmentList.add(fragment)
        titleFragment.add(title)
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence = titleFragment[position]
}