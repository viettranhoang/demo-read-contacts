package com.vit.demoreadcontact;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class Contact implements Comparable<Contact>{

    private String name;
    private String phoneNumber;
    private Bitmap avatar;

    public Contact() {

    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String email, Bitmap avatar) {
        this.name = name;
        this.phoneNumber = email;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    @Override
    public int compareTo(@NonNull Contact o) {
        return this.name.compareTo(o.name);
    }
}
