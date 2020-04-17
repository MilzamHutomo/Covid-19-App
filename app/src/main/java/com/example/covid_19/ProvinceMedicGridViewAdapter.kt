package com.example.covid_19

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ProvinceMedicGridViewAdapter(private val listProvince: ArrayList<Province>, private val path: String) : RecyclerView.Adapter<ProvinceMedicGridViewAdapter.GridViewHolder>() {

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var provinceIcon: ImageView = itemView.findViewById(R.id.feature_icon)
        var provinceName: TextView = itemView.findViewById(R.id.feature_name)
        var provinceCard: LinearLayout = itemView.findViewById(R.id.feature_card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceMedicGridViewAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_grid_generic, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProvince.size
    }

    override fun onBindViewHolder(
        holder: ProvinceMedicGridViewAdapter.GridViewHolder,
        position: Int
    ) {
        val province = listProvince[position]

        holder.provinceName.text = province.value
        holder.provinceCard.setOnClickListener{
            val moveToMedicList = Intent(holder.itemView.context, MedicListPage::class.java)
            moveToMedicList.putExtra("provinceIndex", position.toString())
            moveToMedicList.putExtra("path", path)
            holder.itemView.context.startActivity(moveToMedicList)
        }

        Picasso.get().load(province.imgUrl).into(holder.provinceIcon)
    }
}