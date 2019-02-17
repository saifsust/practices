package org.waodec.geolocation.activities.databases;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DbSingleTon {

    private Context context;
    private DatabaseExperiment dbExperiment;
    private static DbSingleTon instance = null;

    private DbSingleTon(Context context) {
        this.context = context;
        this.dbExperiment = Room.databaseBuilder(context, DatabaseExperiment.class, "MYDATA").build();
    }

    public static DbSingleTon getInstance(Context context) {
        if (instance == null) return new DbSingleTon(context);
        return instance;
    }

    public DatabaseExperiment getDbExperiment() {
        return dbExperiment;
    }
}
