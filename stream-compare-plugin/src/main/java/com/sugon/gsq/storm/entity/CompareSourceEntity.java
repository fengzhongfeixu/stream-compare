package com.sugon.gsq.storm.entity;

import java.io.Serializable;

public class CompareSourceEntity implements Serializable {

    private static final long serialVersionUID = 42611173644298862L;
    private String driver;
    private String url;
    private String username;
    private String pwd;
    private String compareTableName;

    private String compareField;  //kafka中的对比地段集合
    private String compareFieldOfJdbc;    //jdbc中对应的对比字段
    private int sign;   //执行顺序

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCompareTableName() {
        return compareTableName;
    }

    public void setCompareTableName(String compareTableName) {
        this.compareTableName = compareTableName;
    }

    public String getCompareField() {
        return compareField;
    }

    public void setCompareField(String compareField) {
        this.compareField = compareField;
    }

    public String getCompareFieldOfJdbc() {
        return compareFieldOfJdbc;
    }

    public void setCompareFieldOfJdbc(String compareFieldOfJdbc) {
        this.compareFieldOfJdbc = compareFieldOfJdbc;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
