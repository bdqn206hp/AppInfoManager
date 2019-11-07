package priv.simon.springboot.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppVersion {
  private long id;
  private long appId;
  private String versionNo;
  private String versionInfo;
  private long publishStatus;
  private String downloadLink;
  private double versionSize;
  private long createdBy;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date creationDate;
  private long modifyBy;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date modifyDate;
  private String apkLocPath;
  private String apkFileName;
  private AppInfo appInfo;
  private DataDictionary dataDictionary;


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


  public String getVersionNo() {
    return versionNo;
  }

  public void setVersionNo(String versionNo) {
    this.versionNo = versionNo;
  }


  public String getVersionInfo() {
    return versionInfo;
  }

  public void setVersionInfo(String versionInfo) {
    this.versionInfo = versionInfo;
  }


  public long getPublishStatus() {
    return publishStatus;
  }

  public void setPublishStatus(long publishStatus) {
    this.publishStatus = publishStatus;
  }


  public String getDownloadLink() {
    return downloadLink;
  }

  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }


  public double getVersionSize() {
    return versionSize;
  }

  public void setVersionSize(double versionSize) {
    this.versionSize = versionSize;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public String getApkLocPath() {
    return apkLocPath;
  }

  public void setApkLocPath(String apkLocPath) {
    this.apkLocPath = apkLocPath;
  }


  public String getApkFileName() {
    return apkFileName;
  }

  public void setApkFileName(String apkFileName) {
    this.apkFileName = apkFileName;
  }

  public AppInfo getAppInfo() {
    return appInfo;
  }

  public void setAppInfo(AppInfo appInfo) {
    this.appInfo = appInfo;
  }

  public DataDictionary getDataDictionary() {
    return dataDictionary;
  }

  public void setDataDictionary(DataDictionary dataDictionary) {
    this.dataDictionary = dataDictionary;
  }

  @Override
  public String toString() {
    return "AppVersion{" +
            "id=" + id +
            ", appId=" + appId +
            ", versionNo='" + versionNo + '\'' +
            ", versionInfo='" + versionInfo + '\'' +
            ", publishStatus=" + publishStatus +
            ", downloadLink='" + downloadLink + '\'' +
            ", versionSize=" + versionSize +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            ", apkLocPath='" + apkLocPath + '\'' +
            ", apkFileName='" + apkFileName + '\'' +
            ", appInfo=" + appInfo +
            ", dataDictionary=" + dataDictionary +
            '}';
  }
}
