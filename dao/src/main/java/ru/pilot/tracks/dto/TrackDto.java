package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tTrack")
public class TrackDto implements Serializable {
  private static final long serialVersionUID = 1L;
    
  @Id
  @Column(name = "trackId")
  private Long trackId;
  
  @ManyToOne
  @JoinColumn(name = "deviceId")
  private DeviceDto device;

  @Column(name = "name")
  private String name;
  
  @Column(name = "comment")
  private String comment;
  
  @Column(name = "dateStart")
  private Date dateStart;
  
  @Column(name = "dateEnd")
  private Date dateEnd;


  public Long getTrackId() {
    return trackId;
  }
  public void setTrackId(Long trackid) {
    this.trackId = trackId;
  }
  public DeviceDto getDevice() {
    return device;
  }
  public void setDevice(DeviceDto device) {
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
  public Date getDateStart() {
    return dateStart;
  }
  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }
  public Date getDateEnd() {
    return dateEnd;
  }
  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  @Override
  public String toString() {
    return "Point[trackId=" + trackId +
            ", deviceid=" + device.getDeviceId() +
            ", name=" + name +
            ", comment=" + comment +
            ", datestart=" + dateStart +
            ", datend=" + dateEnd +
            "]";
  }

}
