package com.sugon.gsq.scs.entity;

import java.util.Date;

public class LocalUserEntity {

    private Integer id;

    private String userId;

    /*默认为 default */
    private String domainId = "default";

    private String name;

    /*默认为 0 */
    private Integer failedAuthCount = 0;

    private Date failedAuthAt ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFailedAuthCount() {
        return failedAuthCount;
    }

    public void setFailedAuthCount(Integer failedAuthCount) {
        this.failedAuthCount = failedAuthCount;
    }

    public Date getFailedAuthAt() {
        return failedAuthAt;
    }

    public void setFailedAuthAt(Date failedAuthAt) {
        this.failedAuthAt = failedAuthAt;
    }

}
