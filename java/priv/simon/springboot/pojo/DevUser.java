package priv.simon.springboot.pojo;


import java.util.Date;

public class DevUser {

  private long id;
  private String devCode;
  private String devName;
  private String devPassword;
  private String devEmail;
  private String devInfo;
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


  public String getDevCode() {
    return devCode;
  }

  public void setDevCode(String devCode) {
    this.devCode = devCode;
  }


  public String getDevName() {
    return devName;
  }

  public void setDevName(String devName) {
    this.devName = devName;
  }


  public String getDevPassword() {
    return devPassword;
  }

  public void setDevPassword(String devPassword) {
    this.devPassword = devPassword;
  }


  public String getDevEmail() {
    return devEmail;
  }

  public void setDevEmail(String devEmail) {
    this.devEmail = devEmail;
  }


  public String getDevInfo() {
    return devInfo;
  }

  public void setDevInfo(String devInfo) {
    this.devInfo = devInfo;
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

  @Override
  public String toString() {
    return "DevUser{" +
            "id=" + id +
            ", devCode='" + devCode + '\'' +
            ", devName='" + devName + '\'' +
            ", devPassword='" + devPassword + '\'' +
            ", devEmail='" + devEmail + '\'' +
            ", devInfo='" + devInfo + '\'' +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            '}';
  }
}
