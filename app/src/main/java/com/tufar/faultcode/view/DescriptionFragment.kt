package com.tufar.faultcode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tufar.faultcode.R
import com.tufar.faultcode.databinding.FragmentDescriptionBinding
import com.tufar.faultcode.viewModel.CombiDescViewModel


class DescriptionFragment : Fragment() {


    private lateinit var dataBinding : FragmentDescriptionBinding
    private lateinit var viewModel : CombiDescViewModel
    private var faultCode : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_description, container, false)

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_description,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            faultCode = DescriptionFragmentArgs.fromBundle(it).combiFaultName
        }

        viewModel = ViewModelProvider(this).get(CombiDescViewModel::class.java)
        viewModel.getRoomData(faultCode)

        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.combiLiveData.observe(viewLifecycleOwner,Observer{combiDesc ->
            combiDesc?.let {
                dataBinding.selectedCombi = it
            }
        })
    }

}