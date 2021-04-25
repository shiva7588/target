package com.target.targetcasestudy

import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.databinding.DealDetailsFragmentBinding

class DealDetailsFragment(private val dealEntity: DealEntity) : Fragment() {

    private lateinit var dealsDetailBinding: DealDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dealsDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.deal_details_fragment, container, false)
        val view = dealsDetailBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealsDetailBinding.title.text = dealEntity.title
        dealsDetailBinding.desc.text = dealEntity.description
        dealsDetailBinding.salePrice.text = dealEntity.salePrice?.display_string

        val regularPrice = SpannableString(
            String.format(
                getString(R.string.regular_price_strikethrough),
                dealEntity.regularPrice?.display_string
            )
        )
        regularPrice.setSpan(
            StrikethroughSpan(),
            regularPrice.indexOf(dealEntity.regularPrice?.currency_symbol.toString()),
            regularPrice.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        dealsDetailBinding.regularPrice.text = regularPrice

        Glide.with(view).load(dealEntity.imageUrl)
            .into(dealsDetailBinding.pic)
    }
}