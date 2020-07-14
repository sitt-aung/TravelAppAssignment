package com.sa.travelappassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sa.travelappassignment.R
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.delegates.CountryDelegate
import com.sa.travelappassignment.views.viewholders.CountryViewHolder

class CountriesListAdapter(private val delegate: CountryDelegate)
    :BaseRecyclerAdapter<CountryViewHolder, CountryVO>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view, delegate)
    }
}