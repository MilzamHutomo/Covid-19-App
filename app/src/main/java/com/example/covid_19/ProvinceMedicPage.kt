package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ProvinceMedicPage : AppCompatActivity() {

    private lateinit var provinceViewer: RecyclerView
    private var listProvince : ArrayList<Province> = arrayListOf()
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province_medic_page)

        val index: String = intent.getStringExtra("regionIndex")
        val path: String = intent.getStringExtra("featureIndex")
        val newPath = "$path/$index/province"

        database = FirebaseDatabase.getInstance().reference.child(newPath)

        provinceViewer = findViewById(R.id.view_province)

        val provinceListener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(p0: DataSnapshot) {
                var t: GenericTypeIndicator<List<Province>> = object : GenericTypeIndicator<List<Province>>(){

                }

                listProvince = p0.getValue(t) as ArrayList<Province>

                val provinceListAdapter = ProvinceMedicGridViewAdapter( listProvince, newPath )
                provinceViewer.layoutManager = GridLayoutManager(this@ProvinceMedicPage,2)
                provinceViewer.adapter = provinceListAdapter
            }

        }

        database.addValueEventListener( provinceListener )
    }
}
