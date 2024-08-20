package com.Business;

public class Contacts {
    private int Id;
    private String name;
    private String phoneNumber;

    public Contacts (int Id,String name , String pN){
        this.Id = Id;
        this.name= name;
        this.phoneNumber = pN;
    }

    public int getId() { return Id;}
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
