package com.k_duskplayer.my;

import android.support.v4.app.Fragment;

/**
 * Created by K_Dusk on 30/03/2018.
 */

public class Items{
    private String Name;
    private String Location;

    public Items()
    {

    }


    public Items(String name, String location)
    {
        Name = name;
        Location = location;

    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setLocation(String location) {
        Location = location;
    }



}
