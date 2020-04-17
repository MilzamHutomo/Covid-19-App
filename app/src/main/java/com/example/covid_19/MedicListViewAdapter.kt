package com.example.covid_19

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicListViewAdapter(private val listMedic: ArrayList<Medic>) :
    RecyclerView.Adapter<MedicListViewAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicName: TextView = itemView.findViewById(R.id.generic_text_holder)
        val medicCall: Button = itemView.findViewById(R.id.generic_dialer)
        val medicLoc: Button = itemView.findViewById(R.id.generic_location_viewer)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicListViewAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.generic_place_layout, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMedic.size
    }

    override fun onBindViewHolder(holder: MedicListViewAdapter.ListViewHolder, position: Int) {
        val medic = listMedic[position]

        holder.medicName.text = medic.value

        holder.medicCall.setOnClickListener {
            val dialMedic = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${medic.phone}"))
            holder.itemView.context.startActivity(dialMedic)
        }

        holder.medicLoc.setOnClickListener {
            val goToMedic = Intent(Intent.ACTION_VIEW, Uri.parse(medic.location))
            holder.itemView.context.startActivity(goToMedic)
        }
    }
}