package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.MetaData;

import java.util.List;

@Dao
public interface MetaDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMetadata(MetaData metaData);

    @Query("SELECT * FROM MetaData")
    public List<MetaData> getAllMetaData();

    @Query("SELECT value  FROM MetaData WHERE keys=='CRL_ID'")
    public String getCrlMetaData();

    @Query("SELECT value FROM MetaData WHERE keys=='ProgramID'")
    public String getProgramID();
}
