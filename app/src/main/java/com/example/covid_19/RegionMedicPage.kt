package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList

class RegionMedicPage : AppCompatActivity() {

    private lateinit var regionViewer: RecyclerView
    private var listRegion: ArrayList< Region > = arrayListOf()
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_medic_page)

        val pathIndex: String = intent.getStringExtra("featureIndex")
        val path = "covid19/features/$pathIndex/content"

        database = FirebaseDatabase.getInstance().reference.child(path)

        regionViewer = findViewById(R.id.view_region)

        val regionListener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(p0: DataSnapshot) {
                var t: GenericTypeIndicator<List<Region>> = object : GenericTypeIndicator<List<Region>>(){

                }

                listRegion = p0.getValue(t) as ArrayList<Region>
                val regionListAdapter = RegionListViewAdapter(listRegion, path)
                regionViewer.layoutManager = LinearLayoutManager(this@RegionMedicPage)
                regionViewer.adapter = regionListAdapter
            }

        }
        database.addValueEventListener(regionListener)
    }
}
