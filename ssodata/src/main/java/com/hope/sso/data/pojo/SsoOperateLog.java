package com.hope.sso.data.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sso_operate_log")
public class SsoOperateLog {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "app_id")
    private Integer appId;

    private Byte platform;

    private Byte type;

    private String ip;

    private Long latitude;

    private Long longitude;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return platform
     */
    public Byte getPlatform() {
        return platform;
    }

    /**
     * @param platform
     */
    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return latitude
     */
    public Long getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public Long getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}