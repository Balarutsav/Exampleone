package com.example.dhruvil.spit_it_out.Models;

public class MyDBModel {

    public static final String COULUMN_ID = "ID";
    public static final String TABLE_NAME = "Groupmember";
    public static final String COULMN_NUMBER = "Number";
    public static final String COULMN_NAME = "Name";
    //Create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NUMBER INTEGER)");
    public static final String CREATE_TABLE = "Create table" + TABLE_NAME + "(" + COULUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            COULMN_NAME + "TEXT," +
            COULMN_NUMBER + "TEXT" + ")";
    private static final String DATABASE_NAME = "Group";
    private int id;
    private String Number;
    private String Name;

    public MyDBModel(int id, String number, String name) {
        this.id = id;
        this.Number = number;
        this.Name = name;
    }

    public MyDBModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
