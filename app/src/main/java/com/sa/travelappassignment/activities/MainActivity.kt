package com.sa.travelappassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sa.travelappassignment.R
import com.sa.travelappassignment.delegates.CountryDelegate
import com.sa.travelappassignment.delegates.TourDelegate
import com.sa.travelappassignment.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(HomeFragment.newInstance())

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuRating -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuFavourite -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuPrice -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuSearch -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}