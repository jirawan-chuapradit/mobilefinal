package it.ac.kmitl.a59070027;

import android.content.ContentValues;

public class User {

    private String userId;
    private String name;
    private int age;


    ContentValues _row;

    public User() {
        _row = new ContentValues();
    }

    public User(String userId, String name, int age, String password) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.password = password;
    }


    public ContentValues get_row() {
        return _row;
    }

    public void set_row(String userId, String name, int age, String password) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.password = password;

        _row.put("userId", userId);
        _row.put("name", name);
        _row.put("age", userId);
        _row.put("password", password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;



}
