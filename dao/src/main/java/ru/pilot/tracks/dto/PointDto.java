package ru.pilot.tracks.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tPoint")
public class PointDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public PointDto(){
    
  }
  public PointDto(TrackDto track, Date date, BigDecimal x, BigDecimal y, BigDecimal z, BigDecimal speed){
    this.track = track;
    this.date = date;
    this.x = x;
    this.y = y;
    this.z = z;
    this.speed = speed;
  }
  
  @Id
  @ManyToOne
  @JoinColumn(name = "trackId")
  private TrackDto track;
  
  @Id
  @Column(name = "date")
  private Date date;

  @Column(name = "x")
  private BigDecimal x;

  @Column(name = "y")
  private BigDecimal y;

  @Column(name = "z")
  private BigDecimal z;

  @Column(name = "speed")
  private BigDecimal speed;
  
  public BigDecimal getX() {
    return x;
  }
  public void setX(BigDecimal x) {
    this.x = x;
  }
  public BigDecimal getY() {
    return y;
  }
  public void setY(BigDecimal y) {
    this.y = y;
  }
  public BigDecimal getZ() {
    return z;
  }
  public void setZ(BigDecimal z) {
    this.z = z;
  }
  public BigDecimal getSpeed() {
    return speed;
  }
  public void setSpeed(BigDecimal speed) {
    this.speed = speed;
  }
  public TrackDto getTrack() {
    return track;
  }
  public void setTrack(TrackDto track) {
    this.track = track;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  
  @Override
  public String toString() {
    return "Point[trackId=" + track.getTrackId() +
            ", date=" + date +
            ", X=" + x +
            ", Y=" + y +
            ", Z=" + z +
            ", speed=" + speed +
            "]";
  }
}
