package com.example.passwordmanager.model;

public class Item {
    private int id;
    private String webName;
    private String password;
    private String key;
    private String dateItemAdded;

    public Item(int id, String webName, String password, String key, String dateItemAdded) {
        this.id = id;
        this.webName = webName;
        this.password = password;
        this.key = key;
        this.dateItemAdded = dateItemAdded;
    }

    public Item() {
    }

    public Item(String webName, String password, String key, String dateItemAdded) {
        this.webName = webName;
        this.password = password;
        this.key = key;
        this.dateItemAdded = dateItemAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }
}
