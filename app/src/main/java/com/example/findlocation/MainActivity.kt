package com.example.findlocation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findlocation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var appDb:CityDatabase
    lateinit var dao:CityDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb=CityDatabase.getDatabase(this)
        val dao: CityDao = appDb.cityDao()
        val entities: MutableList<City> = ArrayList<City>()
        entities.add(City(null,"Bagan", "Bagan is an ancient city and a UNESCO World Heritage Site in the Mandalay Region of Myanmar.", 21.171520,94.858922))
        entities.add(City(null,"Mandalay", "Mandalay is the second-largest city in Burma.", 21.953140,96.092189))
        entities.add(City(null,"Kalaw", "Kalaw is a hill town in the Shan State of Myanmar. It is located in Kalaw Township in Taunggyi District.Kalaw.", 20.644677,96.562980))
        dao.insertAll(entities)
        var cityName:String=""
        binding.viewCity.setOnClickListener {
            cityName=binding.editTextText.text.toString()

            val city:City?=dao.getCitybyName(cityName)
                if(city==null)
                { Toast.makeText(this,"No such data",Toast.LENGTH_LONG).show()
                }
               else{
                    Toast.makeText(this,city.toString(),Toast.LENGTH_LONG).show()
                    pinLocationMap(city.latitude,city.longitude)
                }

               }


        }
    private fun pinLocationMap(latitude: Double?, longitude: Double?) {
// Create a Uri from an intent string. Open map using intent to pin a specific location (latitude, longitude)
        val mapUri = Uri.parse("https://maps.google.com/maps/search/$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, mapUri)
        startActivity(intent)
    }


    }
