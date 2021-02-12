package com.cebsambagII.onlineinquiries.model;

import android.os.Build;


import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;


public class ComplainantModel implements Serializable {
    private String id;
    private String fullname;
    private String age;
    private String gender;
    private String houseCode;
    private String sitio;
    private String contactNumber;
    private String complaintMessage;
    private String date;

    Calendar calendar = Calendar.getInstance();

    public ComplainantModel() {
    }

    public ComplainantModel(String id, String fullname, String age, String gender, String houseCode, String sitio, String contactNumber, String complaintMessage) {
        this.id = id;
        this.fullname = fullname;
        this.age = age;
        this.gender = gender;
        this.houseCode = houseCode;
        this.sitio = sitio;
        this.contactNumber = contactNumber;
        this.complaintMessage = complaintMessage;

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public String getDate() {
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
