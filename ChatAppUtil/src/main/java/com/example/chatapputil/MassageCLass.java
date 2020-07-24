package com.example.chatapputil;

public class MassageCLass {
    String Text, UserID,UserName;
    MassageCLass(String Text, String UserID, String UserName){
        this.Text = Text;
        this.UserID = UserID;
        this.UserName = UserName;
    }

    public String getText() {
        return Text;
    }

    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }
}
