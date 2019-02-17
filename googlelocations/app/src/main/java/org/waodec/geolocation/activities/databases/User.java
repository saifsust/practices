package org.waodec.geolocation.activities.databases;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "USERS")
public class User {

    @ColumnInfo(name = "USER_ID")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "USER_NAME")
    private String user_name;
    @ColumnInfo(name = "USER_MAIL")
    private String user_mail;

    public User() {
    }

    public User(String user_name, String user_mail) {
        this.user_name = user_name;
        this.user_mail = user_mail;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public String getUser_mail() {
        return this.user_mail;
    }


    public int getId() {
        return this.id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Id " + this.id + " | Usre Name : " + this.user_name + " | User Mail : " + user_mail;
    }
}
