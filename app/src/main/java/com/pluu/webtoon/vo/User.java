package com.pluu.webtoon.vo;

/**
 * Created by SCITMaster on 2018-01-09.
 */

public class User {
    private String id;
    private String pw;
    private String gender;
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;

    public User() {	}

    public User(String id, String pw, String gender, String tag1, String tag2, String tag3, String tag4, String tag5) {
        super();
        this.id = id;
        this.pw = pw;
        this.gender = gender;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.tag5 = tag5;
    }

    public User(String id, String pw, String gender) {
        super();
        this.id = id;
        this.pw = pw;
        this.gender = gender;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getTag5() {
        return tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }

    public String toString() {
        return "id = "+id+", pw = "+pw+", gender = "+gender
                +", tag1 = "+tag1+", tag2 = "+tag2+", tag3 = "+tag3
                +", tag4 = "+tag4+", tag5 = "+tag5;
    }


}
