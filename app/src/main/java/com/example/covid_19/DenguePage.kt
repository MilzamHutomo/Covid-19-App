package com.example.covid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_covid19_page.*
import kotlinx.android.synthetic.main.activity_dengue_page.*

class DenguePage : AppCompatActivity() {
    private lateinit var featureViewer: RecyclerView
    private var listFeature: ArrayList<Features> = arrayListOf()
    private var listBanner: ArrayList<BannerSlide> = arrayListOf()
    lateinit var featureData: DatabaseReference
    lateinit var bannerData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dengue_page)

        val featurePath = "dengue/features"

        featureData = FirebaseDatabase.getInstance().reference.child(featurePath)

        featureViewer = findViewById(R.id.view_dengue_feature)

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
                featureViewer.layoutManager = GridLayoutManager(this@DenguePage, 3)
                featureViewer.adapter = featureListAdapter
            }

        }

        featureData.addValueEventListener(featureListener)

    }
}
