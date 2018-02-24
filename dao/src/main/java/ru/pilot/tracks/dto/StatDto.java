package ru.pilot.tracks.dto;

import ru.pilot.tracks.idProvider.IdProvider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = StatDto.TABLE_NAME)
@TableGenerator(
        table = IdProvider.TABLE,
        pkColumnName = IdProvider.PK_COLUMN,
        valueColumnName = IdProvider.VALUE_COLUMN,
        name = StatDto.TABLE_NAME,
        pkColumnValue = StatDto.TABLE_NAME,
        allocationSize = IdProvider.BATCH_SIZE
)
public class StatDto implements Serializable {
  static final String TABLE_NAME = "tStat";
  private static final long serialVersionUID = 1L;
    
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator=StatDto.TABLE_NAME)
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
