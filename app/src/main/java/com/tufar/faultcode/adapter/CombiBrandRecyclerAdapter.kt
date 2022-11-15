package com.tufar.faultcode.adapter

import android.util.ArraySet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tufar.faultcode.R
import com.tufar.faultcode.databinding.CombiBrandRecyclerRowBinding
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.view.CombiListFragmentDirections
import kotlinx.android.synthetic.main.combi_brand_recycler_row.view.*

class CombiBrandRecyclerAdapter (val combiList : ArrayList<Combi>) : RecyclerView.Adapter<CombiViewHolder>() , CombiClickListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombiViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CombiBrandRecyclerRowBinding>(inflater, R.layout.combi_brand_recycler_row,parent,false)
        return CombiViewHolder(view)
    }

    override fun onBindViewHolder(holder: CombiViewHolder, position: Int) {

        holder.view.combi = combiList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return combiList.size
    }

    fun combiListUpdate(newCombiList : List<Combi>){
        combiList.clear()
        combiList.addAll(newCombiList)
        notifyDataSetChanged()
    }

    override fun combiClicked(view: View) {
        //val uuid = view.combi_uuid.text.toString().toIntOrNull()
        val brand = view.combi_brand.text.toString()
        brand?.let {
            val action = CombiListFragmentDirections.actionCombiListFragmentToCombiModelListFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
    }
}