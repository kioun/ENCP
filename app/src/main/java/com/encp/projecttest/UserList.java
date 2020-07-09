package com.encp.projecttest;

public class UserList {

    String userName;
    String userPosition;
    String userGroup;
    String userRanking;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserRanking() {
        return userRanking;
    }

    public void setUserRanking(String userRanking) {
        this.userRanking = userRanking;
    }

    public UserList(String userName, String userPosition, String userGroup, String userRanking) {
        this.userName = userName;
        this.userPosition = userPosition;
        this.userGroup = userGroup;
        this.userRanking = userRanking;
    }
}
