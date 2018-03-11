package ru.pilot.tracks.dto;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.pilot.tracks.dao.TrackDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tPoint")
public class PointDto extends BaseDto {
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
            ", x=" + x +
            ", y=" + y +
            ", z=" + z +
            ", speed=" + speed +
            "]";
  }

  public static class Serializer implements JsonSerializer<PointDto> {
    @Override
    public JsonElement serialize(PointDto src, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject result = new JsonObject();
      result.addProperty("trackId", src.getTrack().getTrackId());
      result.addProperty("date", src.getDate().toString());
      result.addProperty("x", src.getX());
      result.addProperty("y", src.getY());
      result.addProperty("z", src.getZ());
      result.addProperty("speed", src.getSpeed());
      return result;
    }
  }


  public static class Deserializer implements JsonDeserializer<PointDto>
  {
    @Override
    public PointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
      JsonObject jsonObject = json.getAsJsonObject();
      PointDto pointDto = new PointDto();

      JsonElement element = jsonObject.get("trackId");
      pointDto.setTrack(element != null ? TrackDao.INSTANCE.getTrackInfo(element.getAsLong()) : null);
      
      element = jsonObject.get("date");
      pointDto.setDate(element != null ? strToDate(element.getAsString()) : null);

      element = jsonObject.get("x");
      pointDto.setX(element != null ? element.getAsBigDecimal() : null);
      
      element = jsonObject.get("y");
      pointDto.setY(element != null ? element.getAsBigDecimal() : null);
      
      element = jsonObject.get("z");
      pointDto.setZ(element != null ? element.getAsBigDecimal() : null);
      
      element = jsonObject.get("speed");
      pointDto.setSpeed(element != null ? element.getAsBigDecimal() : null);

      return pointDto;
    }
  }
}
