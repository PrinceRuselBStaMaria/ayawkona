package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    private static final String PREF_NAME = "UserPreferences";

    // Constructor
    public User(int id, String name, String email, String password, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone; 
        this.address = address;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to save user data to SharedPreferences
    public void saveToPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        
        editor.putInt("user_id", id);
        editor.putString("user_name", name);
        editor.putString("user_email", email);
        editor.putString("user_phone", phone);
        editor.putString("user_address", address);
        
        editor.apply();
    }
    
    // Method to load user data from SharedPreferences
    public static User loadFromPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        
        int id = prefs.getInt("user_id", 0);
        String name = prefs.getString("user_name", "");
        String email = prefs.getString("user_email", "");
        String password = ""; // For security, don't store password in SharedPreferences
        String phone = prefs.getString("user_phone", "");
        String address = prefs.getString("user_address", "");
        
        return new User(id, name, email, password, phone, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}