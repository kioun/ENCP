package com.encp.projecttest.list;

public class listitem {

    String Listgroup;
    String Listposition;
    String Listname;

    public listitem(String listgroup, String listposition, String listname) {
        Listgroup = listgroup;
        Listposition = listposition;
        Listname = listname;
    }

    public String getListgroup() {
        return Listgroup;
    }

    public void setListgroup(String listgroup) {
        Listgroup = listgroup;
    }

    public String getListposition() {
        return Listposition;
    }

    public void setListposition(String listposition) {
        Listposition = listposition;
    }

    public String getListname() {
        return Listname;
    }

    public void setListname(String listname) {
        Listname = listname;
    }
}
