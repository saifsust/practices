package org.waodec.geolocation.activities.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = User.class,version = 2)
public abstract class DatabaseExperiment extends RoomDatabase {
    public abstract DbQuery dbQuery();
}
