package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.Date;
import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... entries);

    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> findWeatherByDate(Date date);

    @Query("SELECT count(date) FROM weather WHERE date >= :date")
    int countAllFutureWeather(Date date);

    @Query("SELECT * FROM weather WHERE date >= :date")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ListWeatherEntry>> findWeatherAfterDate(Date date);

    @Query("DELETE FROM weather WHERE DATE < :date")
    void deleteOldWeatherBeforeDate(Date date);
}
