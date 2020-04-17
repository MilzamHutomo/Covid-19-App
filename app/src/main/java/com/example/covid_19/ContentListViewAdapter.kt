package com.example.covid_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContentListViewAdapter( private val listContent: ArrayList< FContent > ) : RecyclerView.Adapter<ContentListViewAdapter.ListViewHolder>(){

    inner class ListViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ){
        val contentValue: TextView = itemView.findViewById(R.id.content_value)
        val contentNumber: TextView = itemView.findViewById(R.id.content_num)
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_feature_content, parent, false)
        return ListViewHolder( view )
    }

    override fun getItemCount(): Int {
        return listContent.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val content = listContent[ position ]

        holder.contentValue.text = content.value
        holder.contentNumber.text = (position+1).toString()
    }

}