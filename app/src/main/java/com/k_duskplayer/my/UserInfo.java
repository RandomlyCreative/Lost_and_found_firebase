package com.k_duskplayer.my;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by K_Dusk on 01/04/2018.
 */

public class UserInfo {
    private String ItemId;
    private String Response;
    private String userName;
    private String userRoom;
    private String userSap;
    private String reqItemLoc;
    private String reqItemName;

public UserInfo(String reqItemName, String reqItemLoc, String response)
{

    this.reqItemName = reqItemName;
    this.reqItemLoc = reqItemLoc;
    this.Response = response;
}



    public UserInfo(String itemId, String response, String userName, String userRoom, String userSap, String reqItemName, String reqItemLoc)
{
    this.ItemId = itemId;
    this.Response= response;
    this.userName= userName;
    this.userRoom= userRoom;
    this.userSap= userSap;
    this.reqItemLoc= reqItemLoc;
    this.reqItemName= reqItemName;

}

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }

    public String getUserSap() {
        return userSap;
    }

    public void setUserSap(String userSap) {
        this.userSap = userSap;
    }

    public String getReqItemLoc() {
        return reqItemLoc;
    }

    public void setReqItemLoc(String reqItemLoc) {
        this.reqItemLoc = reqItemLoc;
    }

    public String getReqItemName() {
        return reqItemName;
    }

    public void setReqItemName(String reqItemName) {
        this.reqItemName = reqItemName;
    }
}
