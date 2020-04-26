package com.example.covid_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DeveloperGridViewAdapter(private val listDev: ArrayList<Developer>) : RecyclerView.Adapter<DeveloperGridViewAdapter.GridViewHolder>() {

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var devProfile: TextView = itemView.findViewById(R.id.dev_profile)
        var devIcon: ImageView = itemView.findViewById(R.id.dev_icon)
        var devRole: TextView = itemView.findViewById(R.id.dev_role)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeveloperGridViewAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.developer_view_layout, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listDev.size
    }

    override fun onBindViewHolder(holder: DeveloperGridViewAdapter.GridViewHolder, position: Int) {
        val dev = listDev[position]
        holder.devProfile.text = dev.profile
        holder.devRole.text = dev.role
        Picasso.get().load(dev.imgUrl).into(holder.devIcon)
    }
}