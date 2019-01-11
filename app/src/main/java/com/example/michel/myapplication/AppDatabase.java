package com.example.michel.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.michel.myapplication.models.ModelCheckBox;

@Database(entities = {ModelCheckBox.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //data acces laag
    public abstract DataAccesLaag checkBoxDoa();

    //database naam
    private final static String NAME_DATABASE = "checkBoxDoa";

    //Static instance
    private static AppDatabase sInstance;

    //maak de appDatabase get instance naam
    public static AppDatabase getInstance(Context context) {
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
        }

        return sInstance;
    }
}