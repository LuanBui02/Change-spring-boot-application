package com.managebankaccount.managebankaccount.details.beans;


import javax.accessibility.AccessibleContext;

public class User  {
    private int id;
    private String name;
    private String birthday;
    private String address;
    private long idNumber;

    public User() {
    }

    public User(int id, String name, String birthday, String address, long idNumber) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNumber = idNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", idNumber=" + idNumber +
                '}';
    }
}
