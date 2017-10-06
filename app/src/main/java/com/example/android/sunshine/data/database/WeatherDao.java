package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... entries);

    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> findWeatherByDate(Date date);

    @Query("SELECT count(date) FROM weather WHERE date >= :date")
    int countAllFutureWeather(Date date);
}
