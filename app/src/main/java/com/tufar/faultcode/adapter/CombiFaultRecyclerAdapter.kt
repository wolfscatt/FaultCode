package com.tufar.faultcode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tufar.faultcode.R
import com.tufar.faultcode.databinding.CombiFaultRecyclerRowBinding
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.view.CombiFaultListFragmentDirections
import kotlinx.android.synthetic.main.combi_fault_recycler_row.view.*

class CombiFaultRecyclerAdapter (val combiList : ArrayList<Combi>) : RecyclerView.Adapter<CombiFaultViewHolder>() , CombiClickListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombiFaultViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CombiFaultRecyclerRowBinding>(inflater, R.layout.combi_fault_recycler_row,parent,false)
        return CombiFaultViewHolder(view)
    }

    override fun onBindViewHolder(holder: CombiFaultViewHolder, position: Int) {

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
       // val uuid = view.combi_uuid.text.toString().toIntOrNull()
        val fault = view.combi_fault.text.toString()

        fault?.let {
            val action = CombiFaultListFragmentDirections.actionCombiFaultListFragmentToDescriptionFragment(it)
            Navigation.findNavController(view).navigate(action)
        }


    }
}
