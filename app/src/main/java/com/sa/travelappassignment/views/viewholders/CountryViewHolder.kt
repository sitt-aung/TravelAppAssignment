package com.sa.travelappassignment.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sa.travelappassignment.data.vos.CountryVO
import com.sa.travelappassignment.delegates.CountryDelegate
import kotlinx.android.synthetic.main.item_country.view.*

class CountryViewHolder(itemView: View, private val delegate: CountryDelegate)
    : BaseViewHolder<CountryVO>(itemView) {

    override fun bindData(data: CountryVO) {
        mData = data
        Glide.with(itemView.context)
            .load(data.photos?.get(0))
            .apply(RequestOptions().centerCrop())
            .into(itemView.ivCountry)
        itemView.tvCountryName.text = data.name
        itemView.tvCountryRating.text = data.averageRating.toString()

        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapCountry(it.name)
            }
        }
    }
}