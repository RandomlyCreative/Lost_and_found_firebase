package com.k_duskplayer.my;

/**
 * Created by K_Dusk on 31/03/2018.
 */

public class UserProfile {

    public String userName;
    public String userSap;
    public String userRoom;

    public UserProfile()
    {

    }


    public UserProfile(String userName, String userSap, String userRoom) {
        this.userName = userName;
        this.userSap = userSap;
        this.userRoom = userRoom;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSap() {
        return userSap;
    }

    public void setUserSap(String userSap) {
        this.userSap = userSap;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }
}
