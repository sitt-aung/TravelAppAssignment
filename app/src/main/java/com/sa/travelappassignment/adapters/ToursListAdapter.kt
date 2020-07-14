package com.sa.travelappassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sa.travelappassignment.R
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.delegates.TourDelegate
import com.sa.travelappassignment.views.viewholders.TourViewHolder

class ToursListAdapter(private val delegate: TourDelegate)
    :BaseRecyclerAdapter<TourViewHolder, TourVO>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour, parent, false)

        return TourViewHolder(view, delegate)
    }
}