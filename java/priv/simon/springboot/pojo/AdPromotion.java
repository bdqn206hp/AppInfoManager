package priv.simon.springboot.pojo;

import java.util.Date;

public class AdPromotion {
    private long id;
    private long appId;
    private String adPicPath;
    private long adPv;
    private long carouselPosition;
    private Date startTime;
    private Date endTime;
    private long createdBy;
    private Date creationDate;
    private long modifyBy;
    private Date modifyDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }


    public String getAdPicPath() {
        return adPicPath;
    }

    public void setAdPicPath(String adPicPath) {
        this.adPicPath = adPicPath;
    }


    public long getAdPv() {
        return adPv;
    }

    public void setAdPv(long adPv) {
        this.adPv = adPv;
    }


    public long getCarouselPosition() {
        return carouselPosition;
    }

    public void setCarouselPosition(long carouselPosition) {
        this.carouselPosition = carouselPosition;
    }


    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
