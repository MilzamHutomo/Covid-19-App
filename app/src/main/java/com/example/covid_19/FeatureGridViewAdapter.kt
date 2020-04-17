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

class FeatureGridViewAdapter(private val listFeature: ArrayList<Features>, private val featurePath: String) :
    RecyclerView.Adapter<FeatureGridViewAdapter.GridViewHolder>() {

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var featureIcon: ImageView = itemView.findViewById(R.id.feature_icon)
        var featureName: TextView = itemView.findViewById(R.id.feature_name)
        var featureCard: LinearLayout = itemView.findViewById(R.id.feature_card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureGridViewAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_grid_generic, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFeature.size
    }

    override fun onBindViewHolder(holder: FeatureGridViewAdapter.GridViewHolder, position: Int) {
        val feature = listFeature[position]
        holder.featureName.text = feature.name

        if ( !feature.name.equals("RUMAH SAKIT RUJUKAN") ) {
            holder.featureCard.setOnClickListener {
                val moveToContent = Intent(holder.itemView.context, ContentPage::class.java)
                moveToContent.putExtra("featureName", feature.name)
                moveToContent.putExtra("featurePath", featurePath)
                moveToContent.putExtra("featureIndex", position.toString())
                holder.itemView.context.startActivity(moveToContent)
            }
        }else{
            holder.featureCard.setOnClickListener{
                val moveToContent = Intent(holder.itemView.context, RegionMedicPage::class.java)
                moveToContent.putExtra("featureIndex", position.toString())
                holder.itemView.context.startActivity(moveToContent)
            }
        }

        Picasso.get().load(feature.imgUrl).into(holder.featureIcon)
    }
}