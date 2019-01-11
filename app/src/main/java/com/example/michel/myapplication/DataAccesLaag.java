package com.example.michel.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.michel.myapplication.models.ModelCheckBox;

import java.util.List;

@Dao
public interface DataAccesLaag {

    @Query("SELECT * FROM listCheckBox")
    public LiveData<List<ModelCheckBox>> getAllStorgeModel();

    @Insert
    public void insertReminders(ModelCheckBox mcb);

    @Delete
    public void deleteReminders(ModelCheckBox mcb);

    @Update
    public void updateReminders(ModelCheckBox mcb);
}
