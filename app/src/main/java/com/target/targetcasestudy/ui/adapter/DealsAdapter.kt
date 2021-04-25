package com.target.targetcasestudy.ui.adapter

import android.app.Activity
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.databinding.DealListItemBinding

interface OnDealItemClickListener {
    fun onDealItemClicked(dealEntity: DealEntity)
}

class DealsAdapter(private val activity: Activity, private val glide: RequestManager) :
    RecyclerView.Adapter<DealsAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(internal val binding: DealListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var repos: MutableList<DealEntity> = mutableListOf()
    private lateinit var itemClickListener: OnDealItemClickListener

    fun setItems(data: List<DealEntity>) {
        this.repos = data as MutableList<DealEntity>
        Handler().run {
            notifyDataSetChanged()
        }
    }
    
    fun setItemClickListener(itemClickListener: OnDealItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DealsAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DealListItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val dealEntity = repos[position]
        holder.binding.executePendingBindings()
        holder.binding.title.text = dealEntity.title
        holder.binding.aisle.text = dealEntity.aisle
        holder.binding.desc.text = dealEntity.description
        holder.binding.price.text = dealEntity.regularPrice?.display_string
        holder.binding.root.setOnClickListener {
            if (itemClickListener != null) {
                itemClickListener.onDealItemClicked(dealEntity)
            }
        }
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
        glide.load(dealEntity.imageUrl)
            .apply(requestOptions)
            .into(holder.binding.pic)
    }



    override fun getItemCount(): Int {
        return repos.size
    }
}