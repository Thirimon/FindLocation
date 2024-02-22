package com.example.findlocation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    fun insertAll(entities: List<City>)
    @Query("Select cityName,cityDiscription,latitude,longitude from city_table where cityName LIKE:cityName ")
    fun getCitybyName(cityName:String):City
}