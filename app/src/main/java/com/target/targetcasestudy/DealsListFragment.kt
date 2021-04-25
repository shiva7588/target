package com.target.targetcasestudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.database.entity.DealEntity
import com.target.targetcasestudy.databinding.DealsListBinding
import com.target.targetcasestudy.ui.adapter.DealsAdapter
import com.target.targetcasestudy.ui.adapter.OnDealItemClickListener
import com.target.targetcasestudy.ui.viewmodel.DealViewModel

class DealsListFragment(private val viewModelFactory: ViewModelProvider.Factory) : Fragment() {


    lateinit var dealViewModel: DealViewModel

    private lateinit var dealsListBinding: DealsListBinding
    private lateinit var dealsAdapter: DealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dealsListBinding =
            DataBindingUtil.inflate(inflater, R.layout.deals_list, container, false)
        val view = dealsListBinding.root
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        dealsListBinding.recyclerView.layoutManager = linearLayoutManager
        dealsListBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
        )
        dealsListBinding.swipeRefreshLayout.setOnRefreshListener {
            dealViewModel.getRepo(true)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealsAdapter = activity?.let { DealsAdapter(it, Glide.with(this)) }!!
        dealsAdapter.setItemClickListener(object : OnDealItemClickListener {
            override fun onDealItemClicked(dealEntity: DealEntity) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container_view, DealDetailsFragment(dealEntity))
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })
        dealsListBinding.recyclerView.adapter = dealsAdapter
        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        dealViewModel = ViewModelProvider(this, viewModelFactory).get(DealViewModel::class.java)

        dealViewModel.getDealsLiveData().observe(viewLifecycleOwner, Observer { resource ->
            if (resource!!.isLoading) {
                dealsListBinding.swipeRefreshLayout.isRefreshing = true
            } else if (resource.data != null && !resource.data.products.isEmpty()) {
                dealsListBinding.recyclerView.smoothScrollToPosition(0)
                dealsAdapter.setItems(resource.data.products)
                dealsListBinding.swipeRefreshLayout.isRefreshing = false
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                dealsListBinding.swipeRefreshLayout.isRefreshing = false
            }
        })
        dealViewModel.getRepo(false)
    }
}