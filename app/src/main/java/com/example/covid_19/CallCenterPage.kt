package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CallCenterPage : AppCompatActivity() {

    private lateinit var callViewer: RecyclerView
    private var listCall: ArrayList< CallCenter > = arrayListOf()
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_center_page)

        database = FirebaseDatabase.getInstance().reference.child("covid19/info_center")

        callViewer = findViewById( R.id.view_call )

        val callListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<List<CallCenter>> = object : GenericTypeIndicator<List<CallCenter>>(){
                }
                listCall = dataSnapshot.getValue(t) as ArrayList<CallCenter>

                val callListAdapter = CallCenterListViewAdapter( listCall )
                callViewer.layoutManager = LinearLayoutManager( this@CallCenterPage )
                callViewer.adapter = callListAdapter

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        database.addValueEventListener( callListener )
    }
}
