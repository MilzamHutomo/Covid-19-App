package com.example.covid_19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MedicListPage : AppCompatActivity() {

    private lateinit var medicViewer: RecyclerView
    private var listMedic: ArrayList<Medic> = arrayListOf()
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medic_list)

        val index: String = intent.getStringExtra("provinceIndex")
        val path: String = intent.getStringExtra("path")

        val newPath = "$path/$index/list"

        database = FirebaseDatabase.getInstance().reference.child(newPath)

        medicViewer = findViewById(R.id.view_medic)

        val medicListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(p0: DataSnapshot) {
                var t: GenericTypeIndicator<List<Medic>> =
                    object : GenericTypeIndicator<List<Medic>>() {

                    }

                listMedic = p0.getValue(t) as ArrayList<Medic>

                val medicListAdapter = MedicListViewAdapter(listMedic)
                medicViewer.layoutManager = LinearLayoutManager(this@MedicListPage)
                medicViewer.adapter = medicListAdapter
            }

        }

        database.addValueEventListener(medicListener)
    }
}
