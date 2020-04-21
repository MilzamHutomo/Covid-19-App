package com.example.covid_19

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_covid19_page.*

class Covid19Page : AppCompatActivity() {

    private lateinit var featureViewer: RecyclerView
    private var listFeature: ArrayList<Features> = arrayListOf()
    private var listBanner: ArrayList<BannerSlide> = arrayListOf()
    lateinit var featureData: DatabaseReference
    lateinit var bannerData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid19_page)

        var btnCallCenter: Button = findViewById(R.id.btn_covid_call)
        btnCallCenter.setOnClickListener {
            val moveToCallCenter = Intent(this@Covid19Page, CallCenterPage::class.java)
            startActivity(moveToCallCenter)
        }

        val featurePath = "covid19/features"

        featureData = FirebaseDatabase.getInstance().reference.child(featurePath)
        bannerData = FirebaseDatabase.getInstance().reference.child("covid19/banner")

        featureViewer = findViewById(R.id.view_feature)


        val bannerListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(p0: DataSnapshot) {
                val t: GenericTypeIndicator<List<BannerSlide>> =
                    object : GenericTypeIndicator<List<BannerSlide>>() {

                    }

                listBanner = p0.getValue(t) as ArrayList<BannerSlide>
                val bannerAdapter = BannerSlideAdapter(listBanner)
                covid19_banner.adapter = bannerAdapter
            }

        }

        bannerData.addValueEventListener(bannerListener)

        val featureListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<List<Features>> =
                    object : GenericTypeIndicator<List<Features>>() {

                    }

                listFeature = dataSnapshot.getValue(t) as ArrayList<Features>
                val featureListAdapter = FeatureGridViewAdapter(listFeature, featurePath)
                featureViewer.layoutManager = GridLayoutManager(this@Covid19Page, 3)
                featureViewer.adapter = featureListAdapter
            }

        }

        featureData.addValueEventListener(featureListener)

    }

}
