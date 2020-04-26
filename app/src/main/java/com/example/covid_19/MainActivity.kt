package com.example.covid_19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(){

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var dataCovid: DatabaseReference
    private lateinit var dataDengue: DatabaseReference
    private var covidData: ArrayList<Covid> = arrayListOf()
    private var dengueData: ArrayList<Dengue> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analytics = FirebaseAnalytics.getInstance(this)

        val btnCovidDetail: Button = findViewById(R.id.covid_detail)
        btnCovidDetail.setOnClickListener{
            val covidDetail = Intent(this@MainActivity, Covid19Page::class.java)
            startActivity(covidDetail)
        }

        val btnDengueDetail: Button = findViewById(R.id.dengue_detail)
        btnDengueDetail.setOnClickListener{
            val dengueDetail = Intent(this@MainActivity, DenguePage::class.java)
            startActivity(dengueDetail)
        }

        val btnDevInfo: ImageView = findViewById(R.id.dev_info)
        btnDevInfo.setOnClickListener{
            val moveToDevInfo = Intent(this@MainActivity, DeveloperInfo::class.java)
            startActivity(moveToDevInfo)
        }

        covidDataGather()
        dengueDataGather()
    }

    private fun covidDataGather() {
        dataCovid = FirebaseDatabase.getInstance().reference.child("covid19/cases")

        val covidDate: TextView = findViewById(R.id.covid_date)
        val covidPositif: TextView = findViewById(R.id.covid_positif)
        val covidDeath: TextView = findViewById(R.id.covid_death)
        val covidRecover: TextView = findViewById(R.id.covid_recover)
        val covidPlusPositif: TextView = findViewById(R.id.covid_plus_positif)
        val covidPlusRecover: TextView = findViewById(R.id.covid_plus_recover)
        val covidPlusDeath: TextView = findViewById(R.id.covid_plus_death)

        val covidListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<List<Covid>> =
                    object : GenericTypeIndicator<List<Covid>>() {
                    }
                covidData = dataSnapshot.getValue(t) as ArrayList<Covid>

                var position: Int = covidData.size
                val covid = covidData[position - 1]
                val covidPrev = covidData[position-2]

                covidDate.text = covid.date
                covidDeath.text = covid.death
                covidPositif.text = covid.positif
                covidRecover.text = covid.recover

                val plusPositif: Int = covid.positif.replace(".","").toInt() - covidPrev.positif.replace(".","").toInt()

                covidPlusPositif.text = (when {
                    plusPositif > 0 -> {
                        "+ $plusPositif"
                    }
                    else -> "$plusPositif"
                }).toString()

                val plusRecover: Int = covid.recover.replace(".","").toInt() - covidPrev.recover.replace(".","").toInt()

                covidPlusRecover.text = (when {
                    plusRecover > 0 -> {
                        "+ $plusRecover"
                    }
                    else -> "$plusRecover"
                }).toString()

                val plusDeath: Int = covid.death.replace(".","").toInt() - covidPrev.death.replace(".","").toInt()

                covidPlusDeath.text = (when {
                    plusDeath > 0 -> {
                        "+ $plusDeath"
                    }
                    else -> "$plusDeath"
                }).toString()

            }

            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }
        }
        dataCovid.addValueEventListener(covidListener)
    }

    private fun dengueDataGather() {
        dataDengue = FirebaseDatabase.getInstance().reference.child("dengue/cases")

        val dengueDate: TextView = findViewById(R.id.dengue_date)
        val dengueInfected: TextView = findViewById(R.id.dengue_infection)
        val dengueDeath: TextView = findViewById(R.id.dengue_death)

        val dengueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<List<Dengue>> =
                    object : GenericTypeIndicator<List<Dengue>>() {
                    }
                dengueData = dataSnapshot.getValue(t) as ArrayList<Dengue>

                var position: Int = dengueData.size
                val dengue = dengueData[position - 1]

                dengueDate.text = dengue.date
                dengueDeath.text = dengue.death
                dengueInfected.text = dengue.infected

            }

            override fun onCancelled(p0: DatabaseError) {
                /*
                    This method will remain empty since the application will never call
                    this method
                 */
            }
        }
        dataDengue.addValueEventListener(dengueListener)
    }
}
