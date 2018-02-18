package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tTrack")
public class TrackDto {
  
  @Id
  @Column(name = "trackid")
  private String trackid;
  
  @ManyToOne
  @JoinColumn(name = "deviceid")
  private DeviceDto device;

  @Column(name = "name")
  private String name;
  
  @Column(name = "comment")
  private String comment;
  
  @Column(name = "datestart")
  private Date datestart;
  
  @Column(name = "datend")
  private Date datend;


  public String getTrackid() {
    return trackid;
  }
  public void setTrackid(String trackid) {
    this.trackid = trackid;
  }
  public DeviceDto getDevice() {
    return device;
  }
  public void setDeviceid(DeviceDto device) {
    this.device = device;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public Date getDatestart() {
    return datestart;
  }
  public void setDatestart(Date datestart) {
    this.datestart = datestart;
  }
  public Date getDatend() {
    return datend;
  }
  public void setDatend(java.sql.Timestamp datend) {
    this.datend = datend;
  }

  @Override
  public String toString() {
    return "Point[trackId=" + trackid +
            ", deviceid=" + device.getDeviceid() +
            ", name=" + name +
            ", comment=" + comment +
            ", datestart=" + datestart +
            ", datend=" + datend +
            "]";
  }

}
