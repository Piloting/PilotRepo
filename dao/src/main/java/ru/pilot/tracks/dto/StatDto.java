package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tStat")
public class StatDto {
  
  @OneToOne
  @JoinColumn(name = "trackid")
  private TrackDto track;

  @Column(name = "len")
  private String len;

  @Column(name = "time")
  private String time;

  @Column(name = "avgspeed")
  private String avgspeed;

  @Column(name = "maxspeed")
  private String maxspeed;

  @Column(name = "stopcount")
  private String stopcount;
  
  @Column(name = "maxheight")
  private String maxheight;
  
  @Column(name = "minheight")
  private String minheight;

  @Column(name = "movetime")
  private String movetime;
  
  public TrackDto getTrack() {
    return track;
  }
  public void setTrack(TrackDto trackid) {
    this.track = track;
  }
  public String getLen() {
    return len;
  }
  public void setLen(String len) {
    this.len = len;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getAvgspeed() {
    return avgspeed;
  }
  public void setAvgspeed(String avgspeed) {
    this.avgspeed = avgspeed;
  }
  public String getMaxspeed() {
    return maxspeed;
  }
  public void setMaxspeed(String maxspeed) {
    this.maxspeed = maxspeed;
  }
  public String getStopcount() {
    return stopcount;
  }
  public void setStopcount(String stopcount) {
    this.stopcount = stopcount;
  }
  public String getMaxheight() {
    return maxheight;
  }
  public void setMaxheight(String maxheight) {
    this.maxheight = maxheight;
  }
  public String getMinheight() {
    return minheight;
  }
  public void setMinheight(String minheight) {
    this.minheight = minheight;
  }
  public String getMovetime() {
    return movetime;
  }
  public void setMovetime(String movetime) {
    this.movetime = movetime;
  }
  
  @Override
  public String toString() {
    return "Point[trackId=" + track.getTrackid() +
            ", len=" + len +
            ", time=" + time +
            ", avgspeed=" + avgspeed +
            ", maxspeed=" + maxspeed +
            ", stopcount=" + stopcount +
            ", maxheight=" + maxheight +
            ", minheight=" + minheight +
            ", movetime=" + movetime +
            "]";
  }
}
