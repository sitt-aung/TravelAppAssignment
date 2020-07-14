package com.sa.travelappassignment.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sa.travelappassignment.data.vos.TourVO
import com.sa.travelappassignment.delegates.TourDelegate
import kotlinx.android.synthetic.main.item_tour.view.*

class TourViewHolder(itemView: View, private val delegate: TourDelegate)
    :BaseViewHolder<TourVO>(itemView)
{
    override fun bindData(data: TourVO) {
        mData = data
        Glide.with(itemView.context)
            .load(data.photos?.get(0))
            .apply(RequestOptions().centerCrop())
            .into(itemView.ivTour)

        itemView.tvTourName.text = data.name
        itemView.tvTourRating.text = data.averageRating.toString()

        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapTour(it.name)
            }
        }
    }
}