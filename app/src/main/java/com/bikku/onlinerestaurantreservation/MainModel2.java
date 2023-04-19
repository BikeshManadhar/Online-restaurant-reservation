package com.bikku.onlinerestaurantreservation;

public class MainModel2 {
    String table_description, t_type, t_capacity, t_location, turl;

    MainModel2()
    {

    }

    public MainModel2(String table_description, String t_type, String t_capacity, String t_location, String turl) {
        this.table_description = table_description;
        this.t_type = t_type;
        this.t_capacity = t_capacity;
        this.t_location = t_location;
        this.turl = turl;
    }

    public String getTable_description() {
        return table_description;
    }

    public void setTable_description(String table_description) {
        this.table_description = table_description;
    }

    public String getT_type() {
        return t_type;
    }

    public void setT_type(String t_type) {
        this.t_type = t_type;
    }

    public String getT_capacity() {
        return t_capacity;
    }

    public void setT_capacity(String t_capacity) {
        this.t_capacity = t_capacity;
    }

    public String getT_location() {
        return t_location;
    }

    public void setT_location(String t_location) {
        this.t_location = t_location;
    }


    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }
}
