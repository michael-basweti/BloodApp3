package com.example.user.bloodapp3;

/**
 * Created by user on 6/10/2017.
 */

public class Blog1 {



    private String Blood_Group;
    private String Nearest_Hospital;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String image;

    public Blog1(){

    }

    public Blog1(String blood_Group, String nearest_Hospital, String name, String address, String phone, String email, String image) {
        Blood_Group = blood_Group;
        Nearest_Hospital = nearest_Hospital;
        Name = name;
        Address = address;
        Phone = phone;
        Email = email;
        this.image = image;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        Blood_Group = blood_Group;
    }

    public String getNearest_Hospital() {
        return Nearest_Hospital;
    }

    public void setNearest_Hospital(String nearest_Hospital) {
        Nearest_Hospital = nearest_Hospital;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

