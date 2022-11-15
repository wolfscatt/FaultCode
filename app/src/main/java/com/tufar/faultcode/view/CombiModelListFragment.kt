package com.tufar.faultcode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tufar.faultcode.R
import com.tufar.faultcode.adapter.CombiModelRecyclerAdapter
import com.tufar.faultcode.databinding.CombiModelRecyclerRowBinding
import com.tufar.faultcode.viewModel.CombiModelListViewModel
import kotlinx.android.synthetic.main.fragment_combi_list.*
import kotlinx.android.synthetic.main.fragment_combi_model_list.*


class CombiModelListFragment : Fragment() {

    //private lateinit var dataBinding: CombiModelRecyclerRowBinding
    private lateinit var viewModel : CombiModelListViewModel
    private var combiId = 0
    private var combiBrandname : String? = null
    private var recyclerCombiModelAdapter = CombiModelRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_combi_model_list, container, false)
        //dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_combi_model_list,container,false)
        //return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


/*
        arguments?.let {
            combiId = CombiModelListFragmentArgs.fromBundle(it).combiId
        }
 */
        arguments?.let {
            combiBrandname = CombiModelListFragmentArgs.fromBundle(it).combiBrandName
        }


        modelListRecycler.layoutManager = LinearLayoutManager(context)
        modelListRecycler.adapter = recyclerCombiModelAdapter


        viewModel = ViewModelProvider(this).get(CombiModelListViewModel::class.java)
        viewModel.getRoomData(combiBrandname)

        observeLiveData()


    }

    fun observeLiveData(){

        viewModel.combiLiveData.observe(viewLifecycleOwner,Observer{combies ->
            combies?.let {
                modelListRecycler.visibility = View.VISIBLE
                recyclerCombiModelAdapter.combiListUpdate(combies)
            }
        })
    }
}