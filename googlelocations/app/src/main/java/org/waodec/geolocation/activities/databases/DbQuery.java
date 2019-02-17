package org.waodec.geolocation.activities.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DbQuery {

    @Query(value = "SELECT * FROM USERS WHERE USER_ID=:id")
    public User getUserById(int id);

    @Insert
    public void insert(User usre);

    @Query(value = "SELECT * FROM USERS")
    public List<User> retrieveAll();

    @Query(value = "DELETE FROM USERS WHERE USER_ID=:id")
    public void delete(int id);


}
