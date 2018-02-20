package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tDevice")
public class DeviceDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "deviceId")
  private Long deviceId;

  @ManyToOne
  @JoinColumn(name = "userId")
  private UserDto user;

  @Column(name = "brief")
  private String brief;

  @Column(name = "comment")
  private String comment;


  public Long getDeviceId() {
    return deviceId;
  }
  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
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
    return "Device[id="+ deviceId +
            ", userId="+user.getUserId()+
            ", brief="+brief+
            ", comment="+comment+
            "]";
  }
}
