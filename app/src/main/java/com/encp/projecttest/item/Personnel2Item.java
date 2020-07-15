package com.encp.projecttest.item;

public class Personnel2Item {

    String personnel2ID;
    String personnel2Name;
    String personnel2Group;
    String personnel2Position;

    public Personnel2Item(String personnel2ID, String personnel2Name, String personnel2Group, String personnel2Position) {
        this.personnel2ID = personnel2ID;
        this.personnel2Name = personnel2Name;
        this.personnel2Group = personnel2Group;
        this.personnel2Position = personnel2Position;
    }

    public String getPersonnel2ID() {
        return personnel2ID;
    }

    public void setPersonnel2ID(String personnel2ID) {
        this.personnel2ID = personnel2ID;
    }

    public String getPersonnel2Name() {
        return personnel2Name;
    }

    public void setPersonnel2Name(String personnel2Name) {
        this.personnel2Name = personnel2Name;
    }

    public String getPersonnel2Group() {
        return personnel2Group;
    }

    public void setPersonnel2Group(String personnel2Group) {
        this.personnel2Group = personnel2Group;
    }

    public String getPersonnel2Position() {
        return personnel2Position;
    }

    public void setPersonnel2Position(String personnel2Position) {
        this.personnel2Position = personnel2Position;
    }
}
