package ru.pilot.tracks.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tStat")
public class StatDto implements Serializable {
  private static final long serialVersionUID = 1L;
    
  @Id
  @Column(name = "statId")
  private Long statId;
  
  @OneToOne
  @JoinColumn(name = "trackId")
  private TrackDto track;

  @Column(name = "len")
  private BigDecimal len;

  @Column(name = "Time")
  private Long time;
  
  @Column(name = "moveTime")
  private Long moveTime;
  
  @Column(name = "avgSpeed")
  private BigDecimal avgSpeed;

  @Column(name = "maxSpeed")
  private BigDecimal maxSpeed;

  @Column(name = "stopCount")
  private Long stopCount;
  
  @Column(name = "maxHeight")
  private Long maxHeight;
  
  @Column(name = "minHeight")
  private Long minHeight;
  
  public TrackDto getTrack() {
    return track;
  }
  public void setTrack(TrackDto trackid) {
    this.track = track;
  }
  public BigDecimal getLen() {
    return len;
  }
  public void setLen(BigDecimal len) {
    this.len = len;
  }
  public Long getTime() {
    return time;
  }
  public void setTime(Long  Time) {
    this.time = Time;
  }
  public BigDecimal getAvgSpeed() {
    return avgSpeed;
  }
  public void setAvgSpeed(BigDecimal avgSpeed) {
    this.avgSpeed = avgSpeed;
  }
  public BigDecimal getMaxSpeed() {
    return maxSpeed;
  }
  public void setMaxSpeed(BigDecimal maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
  public Long getStopcount() {
    return stopCount;
  }
  public void setStopcount(Long stopCount) {
    this.stopCount = stopCount;
  }
  public Long getMaxHeight() {
    return maxHeight;
  }
  public void setMaxHeight(Long maxHeight) {
    this.maxHeight = maxHeight;
  }
  public Long getMinHeight() {
    return minHeight;
  }
  public void setMinHeight(Long minHeight) {
    this.minHeight = minHeight;
  }
  public Long getMoveTime() {
    return moveTime;
  }
  public void setMoveTime(Long moveTime) {
    this.moveTime = moveTime;
  }
  
  public Long getStatId() {
    return statId;
  }

  public void setStatId(Long statId) {
    this.statId = statId;
  }
  
  @Override
  public String toString() {
    return "Point[statId=" + statId +
            "trackId=" + track.getTrackId() +
            ", len=" + len +
            ", time=" + time +
            ", avgSpeed=" + avgSpeed +
            ", maxSpeed=" + maxSpeed +
            ", stopCount=" + stopCount +
            ", maxHeight=" + maxHeight +
            ", minHeight=" + minHeight +
            ", moveTime=" + moveTime +
            "]";
  }
}
