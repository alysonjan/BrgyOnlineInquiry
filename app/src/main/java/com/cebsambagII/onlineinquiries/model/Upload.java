package com.cebsambagII.onlineinquiries.model;



import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

public class Upload implements Serializable {
    private String mCaption;
    private String mImageUrl;
    private String id;
    private String date;

    Calendar calendar = Calendar.getInstance();


    public Upload() {
    }


    public Upload(String caption, String mImageUrl,String id) {
        if (caption.trim().equals("")) {
            caption = "No Caption";
        }
        this.mCaption = caption;
        this.mImageUrl = mImageUrl;
        this.id = id;


    }

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
