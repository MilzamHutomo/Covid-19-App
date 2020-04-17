package com.example.covid_19

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RegionListViewAdapter(private val listRegion: ArrayList<Region>, private val index: String) : RecyclerView.Adapter<RegionListViewAdapter.ListViewHolder>() {

    inner class ListViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView ){
        val listText: TextView = itemView.findViewById(R.id.list_text)
        val listBar: RelativeLayout = itemView.findViewById(R.id.list_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RegionListViewAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_unordered_generic, parent, false)
        return ListViewHolder( view )
    }

    override fun getItemCount(): Int {
        return listRegion.size
    }

    override fun onBindViewHolder(holder: RegionListViewAdapter.ListViewHolder, position: Int) {
        val region = listRegion[position]

        holder.listText.text = region.value
        holder.listBar.setOnClickListener{
            val moveToProvince = Intent(holder.itemView.context, ProvinceMedicPage::class.java)
            moveToProvince.putExtra("regionIndex", position.toString())
            moveToProvince.putExtra("featureIndex", index)
            holder.itemView.context.startActivity( moveToProvince )
        }
    }
}