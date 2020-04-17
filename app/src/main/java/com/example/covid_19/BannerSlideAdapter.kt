package com.example.covid_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BannerSlideAdapter(private val listBanner: ArrayList<BannerSlide>) : RecyclerView.Adapter<BannerSlideAdapter.SlideViewHolder>(){

    inner class SlideViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        val bannerImg: ImageView = itemView.findViewById(R.id.view_image_slide)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerSlideAdapter.SlideViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.banner_slide_container, parent, false)
        return SlideViewHolder( view )
    }

    override fun getItemCount(): Int {
        return listBanner.size
    }

    override fun onBindViewHolder(holder: BannerSlideAdapter.SlideViewHolder, position: Int) {
        val bannerItem = listBanner[position]

        Picasso.get().load(bannerItem.imgUrl).into(holder.bannerImg)
    }
}