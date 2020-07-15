package com.encp.projecttest.list;

public class listitem {

    String Listgroup;
    String Listposition;
    String Listname;
    private int Listdraftercount;
    private int Listreviewcount;
    private int Listpaymentcount;

    public listitem(String listgroup, String listposition, String listname, int listdraftercount, int listreviewcount, int listpaymentcount) {
        Listgroup = listgroup;
        Listposition = listposition;
        Listname = listname;
        Listdraftercount = listdraftercount;
        Listreviewcount = listreviewcount;
        Listpaymentcount = listpaymentcount;
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

    public int getListdraftercount() {
        return Listdraftercount;
    }

    public void setListdraftercount(int listdraftercount) {
        Listdraftercount = listdraftercount;
    }

    public int getListreviewcount() {
        return Listreviewcount;
    }

    public void setListreviewcount(int listreviewcount) {
        Listreviewcount = listreviewcount;
    }

    public int getListpaymentcount() {
        return Listpaymentcount;
    }

    public void setListpaymentcount(int listpaymentcount) {
        Listpaymentcount = listpaymentcount;
    }
}
