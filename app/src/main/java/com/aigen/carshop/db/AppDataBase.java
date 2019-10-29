package com.aigen.carshop.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aigen.carshop.db.model.carmodel;


@Database(entities = {carmodel.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract mDao myDAO();
}
