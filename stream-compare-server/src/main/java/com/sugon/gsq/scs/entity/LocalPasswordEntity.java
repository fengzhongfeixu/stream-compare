package com.sugon.gsq.scs.entity;

import java.util.Date;

public class LocalPasswordEntity {
    private Integer id;

    private Integer localUserId;

    private String password;

    private Date expiresAt;

    /*默认为 false*/
    private Boolean selfService = false;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(Integer localUserId) {
        this.localUserId = localUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getSelfService() {
        return selfService;
    }

    public void setSelfService(Boolean selfService) {
        this.selfService = selfService;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
