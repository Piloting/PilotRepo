package ru.pilot.tracks.dto;

import ru.pilot.tracks.idProvider.IdProvider;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = TrackDto.TABLE_NAME)
@TableGenerator(
        table = IdProvider.TABLE,
        pkColumnName = IdProvider.PK_COLUMN,
        valueColumnName = IdProvider.VALUE_COLUMN,
        name = TrackDto.TABLE_NAME,
        pkColumnValue = TrackDto.TABLE_NAME,
        allocationSize = IdProvider.BATCH_SIZE
)
public class TrackDto extends BaseDto {
  static final String TABLE_NAME = "tTrack";
  private static final long serialVersionUID = 1L;
    
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator=TrackDto.TABLE_NAME)
  @Column(name = "trackId")
  private Long trackId;
  
  @ManyToOne(fetch = FetchType.LAZY)
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
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "track", cascade = CascadeType.ALL)
  transient private List<PointDto> pointDtoList;
  
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
  public List<PointDto> getPointDtoList() {
    if (pointDtoList == null){
      pointDtoList = new ArrayList<>();
    }
    return pointDtoList;
  }
  public void setPointDtoList(List<PointDto> pointDtoList) {
    if (pointDtoList == null){
      pointDtoList = new ArrayList<>();
    }
    this.pointDtoList = pointDtoList;
  }
  
  @Override
  public String toString() {
    return "Track[trackId=" + trackId +
            ", deviceid=" + device.getDeviceId() +
            ", name=" + name +
            ", comment=" + comment +
            ", datestart=" + dateStart +
            ", datend=" + dateEnd +
            "]";
  }
}
