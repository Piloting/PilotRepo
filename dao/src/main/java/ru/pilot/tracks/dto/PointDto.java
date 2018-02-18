package ru.pilot.tracks.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tPoint")
public class PointDto {

  @ManyToOne
  @JoinColumn(name = "trackid")
  private TrackDto track;

  @Column(name = "date")
  private Date date;

  @Column(name = "X")
  private String X;

  @Column(name = "Y")
  private String Y;

  @Column(name = "Z")
  private String Z;

  @Column(name = "speed")
  private String speed;
  
  public TrackDto getTrack() {
    return track;
  }
  public void setTrack(TrackDto track) {
    this.track = track;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(java.sql.Date date) {
    this.date = date;
  }
  public String getX() {
    return X;
  }
  public void setX(String X) {
    this.X = X;
  }
  public String getY() {
    return Y;
  }
  public void setY(String Y) {
    this.Y = Y;
  }
  public String getZ() {
    return Z;
  }
  public void setZ(String Z) {
    this.Z = Z;
  }
  public String getSpeed() {
    return speed;
  }
  public void setSpeed(String speed) {
    this.speed = speed;
  }

  @Override
  public String toString() {
    return "Point[trackId=" + track.getTrackid() +
            ", date=" + date +
            ", X=" + X +
            ", Y=" + Y +
            ", Z=" + Z +
            ", speed=" + speed +
            "]";
  }
}
