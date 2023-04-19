package com.bikku.onlinerestaurantreservation;

public class MainModel3 {

    String roomno, roomcapacity, roomtype, rurl;

    MainModel3()
    {

    }

    public MainModel3(String roomno, String roomcapacity, String roomtype, String rurl) {
        this.roomno = roomno;
        this.roomcapacity = roomcapacity;
        this.roomtype = roomtype;
        this.rurl = rurl;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRoomcapacity() {
        return roomcapacity;
    }

    public void setRoomcapacity(String roomcapacity) {
        this.roomcapacity = roomcapacity;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
    }
}
