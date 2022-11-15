package com.tufar.faultcode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tufar.faultcode.R
import com.tufar.faultcode.adapter.CombiBrandRecyclerAdapter
import com.tufar.faultcode.viewModel.CombiListViewModel
import kotlinx.android.synthetic.main.fragment_combi_list.*

class CombiListFragment : Fragment() {

    private lateinit var viewModel : CombiListViewModel
    private val recyclerCombiBrandAdapter = CombiBrandRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_combi_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CombiListViewModel::class.java)
        viewModel.refreshData()

        brandListRecycler.layoutManager = LinearLayoutManager(context)
        brandListRecycler.adapter = recyclerCombiBrandAdapter

        swipeRefreshLayout.setOnRefreshListener {
            combiLoading.visibility = View.VISIBLE
            combiHataText.visibility = View.GONE
            brandListRecycler.visibility = View.GONE
            viewModel.refreshFromWeb()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()



    }

    fun observeLiveData(){
        viewModel.combies.observe(viewLifecycleOwner,Observer{ combies ->
            combies?.let {

                brandListRecycler.visibility = View.VISIBLE
                recyclerCombiBrandAdapter.combiListUpdate(combies)
            }
        })

        viewModel.combiFaultMessage.observe(viewLifecycleOwner,Observer{ hata ->
            hata?.let {
                if (it){
                    brandListRecycler.visibility = View.GONE
                    combiHataText.visibility = View.VISIBLE
                }
                else{
                    combiHataText.visibility = View.GONE
                }
            }
        })

        viewModel.combiLoading.observe(viewLifecycleOwner,Observer{yukleniyor ->
            yukleniyor?.let {
                if (it){
                    brandListRecycler.visibility = View.GONE
                    combiHataText.visibility = View.GONE
                    combiLoading.visibility = View.VISIBLE
                }
                else{
                    combiLoading.visibility = View.GONE
                }
            }
        })
    }
}