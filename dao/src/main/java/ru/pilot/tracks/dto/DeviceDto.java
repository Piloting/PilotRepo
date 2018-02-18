package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tDevice")
public class DeviceDto {
  
  @Id
  @Column(name = "deviceid")
  private String deviceid;

  @ManyToOne
  @JoinColumn(name = "userid")
  private UserDto user;

  @Column(name = "brief")
  private String brief;

  @Column(name = "comment")
  private String comment;


  public String getDeviceid() {
    return deviceid;
  }
  public void setDeviceid(String deviceid) {
    this.deviceid = deviceid;
  }
  public UserDto getUser() {
    return user;
  }
  public void setUser(UserDto user) {
    this.user = user;
  }
  public String getBrief() {
    return brief;
  }
  public void setBrief(String brief) {
    this.brief = brief;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public String toString() {
    return "Device[id="+deviceid+
            ", userId="+user.getUserId()+
            ", brief="+brief+
            ", comment="+comment+
            "]";
  }
}
