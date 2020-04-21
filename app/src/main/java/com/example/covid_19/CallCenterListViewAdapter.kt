package com.example.covid_19

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CallCenterListViewAdapter(private val listCallCenter: ArrayList<CallCenter>) :
    RecyclerView.Adapter<CallCenterListViewAdapter.ListViewHolder>(){

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val callArea: TextView = itemView.findViewById(R.id.call_area)
        val callNumber: TextView = itemView.findViewById(R.id.call_number)
        val callWeb: TextView = itemView.findViewById(R.id.call_web)
        val btnDial: Button = itemView.findViewById(R.id.btn_call_dial)
        val btnWeb: Button = itemView.findViewById(R.id.btn_call_web)
        val callIcon: ImageView = itemView.findViewById(R.id.call_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_call_center, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCallCenter.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val callCenter = listCallCenter[position]

        holder.callArea.text = callCenter.area
        holder.callNumber.text = callCenter.callCenter
        holder.callWeb.text = callCenter.website

        holder.btnDial.setOnClickListener {
            val number = callCenter.callCenter
            val dialCallCenter = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            holder.itemView.context.startActivity(dialCallCenter)
        }

        holder.btnWeb.setOnClickListener {
            val website = callCenter.website
            val openBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(website))
            holder.itemView.context.startActivity(openBrowser)
        }

        Picasso.get().load(callCenter.imgUrl).into(holder.callIcon)
    }

}