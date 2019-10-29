package com.aigen.carshop.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.aigen.carshop.db.model.carmodel;
import java.util.List;

@Dao
public interface mDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addDummyData(carmodel cm);

    @Query("select * from carmodelTable")
    public LiveData<List<carmodel>> getAllCarList();

    @Query("select * from carmodelTable where id = :cid")
    public carmodel getSpecificCarDetail(int cid);
}
