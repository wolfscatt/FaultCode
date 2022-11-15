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
import com.tufar.faultcode.adapter.CombiFaultRecyclerAdapter
import com.tufar.faultcode.viewModel.CombiFaultListViewModel
import com.tufar.faultcode.viewModel.CombiModelListViewModel
import kotlinx.android.synthetic.main.fragment_combi_fault_list.*
import kotlinx.android.synthetic.main.fragment_combi_model_list.*

class CombiFaultListFragment : Fragment() {


    private lateinit var viewModel : CombiFaultListViewModel
    private var combiModelName : String? = null
    private var recyclerCombiFaultAdapter = CombiFaultRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_combi_fault_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            combiModelName = CombiFaultListFragmentArgs.fromBundle(it).combiModelName
        }


        faultListRecycler.layoutManager = LinearLayoutManager(context)
        faultListRecycler.adapter = recyclerCombiFaultAdapter


        viewModel = ViewModelProvider(this).get(CombiFaultListViewModel::class.java)
        viewModel.getRoomData(combiModelName)

        observeLiveData()
    }

    fun observeLiveData(){

        viewModel.combiLiveData.observe(viewLifecycleOwner, Observer{ combies ->
            combies?.let {
                faultListRecycler.visibility = View.VISIBLE
                recyclerCombiFaultAdapter.combiListUpdate(combies)
            }
        })
    }
}