package me.lesovoy.blog;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Post implements Serializable {
    public int id;
    public String text;
    public Timestamp date;


    Post(int id, Timestamp date, String text){
        this.id = id;
        this.date = date;
        this.text = text;
    }


}
