package com.tufar.faultcode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tufar.faultcode.R
import com.tufar.faultcode.databinding.CombiModelRecyclerRowBinding
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.view.CombiModelListFragmentDirections
import kotlinx.android.synthetic.main.combi_model_recycler_row.view.*

class CombiModelRecyclerAdapter (val combiList : ArrayList<Combi>) : RecyclerView.Adapter<CombiModelViewHolder>() , CombiClickListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombiModelViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CombiModelRecyclerRowBinding>(inflater, R.layout.combi_model_recycler_row,parent,false)
        return CombiModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: CombiModelViewHolder, position: Int) {

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
        val modelName = view.combi_model.text.toString()

        modelName?.let {
            val action = CombiModelListFragmentDirections.actionCombiModelListFragmentToCombiFaultListFragment(it)
            Navigation.findNavController(view).navigate(action)
        }


    }
}
