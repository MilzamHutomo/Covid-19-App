package com.example.covid_19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class DeveloperInfo : AppCompatActivity() {

    private lateinit var devViewer: RecyclerView
    private var listDev: ArrayList<Developer> = arrayListOf()
    lateinit var devData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_info)

        devData = FirebaseDatabase.getInstance().reference.child("developer")

        devViewer = findViewById(R.id.view_developer)

        val devListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(p0: DataSnapshot) {
                val t: GenericTypeIndicator<List<Developer>> =
                    object : GenericTypeIndicator<List<Developer>>() {

                    }

                listDev = p0.getValue(t) as ArrayList<Developer>
                val devListAdapter = DeveloperGridViewAdapter(listDev)
                devViewer.layoutManager = GridLayoutManager(this@DeveloperInfo, 2)
                devViewer.adapter = devListAdapter
            }

        }

        devData.addValueEventListener(devListener)

    }
}
