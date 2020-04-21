package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ContentPage : AppCompatActivity() {

    private lateinit var contentViewer: RecyclerView
    private var listContent: ArrayList< FContent > = arrayListOf()
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_page)

        val header: String = intent.getStringExtra("featureName")
        val pathIndex: String = intent.getStringExtra("featureIndex")
        val featurePath: String = intent.getStringExtra("featurePath")

        val contentHeader: TextView = findViewById(R.id.feature_header)
        contentHeader.text = header

        database = FirebaseDatabase.getInstance().reference.child("$featurePath/$pathIndex/content")

        contentViewer = findViewById(R.id.view_content)

        val contentListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<List<FContent>> = object : GenericTypeIndicator<List<FContent>>(){

                }
                listContent = dataSnapshot.getValue(t) as ArrayList<FContent>

                val contentListAdapter = ContentListViewAdapter(listContent)
                contentViewer.layoutManager = LinearLayoutManager(this@ContentPage)
                contentViewer.adapter = contentListAdapter
            }

        }
        database.addValueEventListener(contentListener)
    }
}
