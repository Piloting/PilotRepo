package ru.pilot.tracks.dto;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.pilot.tracks.dao.UserDao;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DeviceDto.TABLE_NAME)
@TableGenerator(
        table = IdProvider.TABLE,
        pkColumnName = IdProvider.PK_COLUMN,
        valueColumnName = IdProvider.VALUE_COLUMN,
        name = DeviceDto.TABLE_NAME,
        pkColumnValue = DeviceDto.TABLE_NAME,
        allocationSize = IdProvider.BATCH_SIZE
)
public class DeviceDto extends BaseDto {
  static final String TABLE_NAME = "tDevice";
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator=DeviceDto.TABLE_NAME)
  @Column(name = "deviceId")
  private Long deviceId;

  @ManyToOne
  @JoinColumn(name = "userId")
  private UserDto user;

  @Column(name = "brief")
  private String brief;

  @Column(name = "comment")
  private String comment;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = CascadeType.ALL)
  transient private List<TrackDto> trackDtoList;

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
  public List<TrackDto> getTrackDtoList() {
    if (trackDtoList == null){
      trackDtoList = new ArrayList<>();
    }
    return trackDtoList;
  }
  public void setTrackDtoList(List<TrackDto> trackDtoList) {
    this.trackDtoList = trackDtoList;
  }

  @Override
  public String toString() {
    return "Device[id="+ deviceId +
            ", userId="+user.getUserId()+
            ", brief="+brief+
            ", comment="+comment+
            "]";
  }

  public static class Serializer implements JsonSerializer<DeviceDto> {
    @Override
    public JsonElement serialize(DeviceDto src, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject result = new JsonObject();
      result.addProperty("deviceId", src.getDeviceId());
      result.addProperty("userId", src.getUser().getUserId());
      result.addProperty("brief", src.getBrief());
      result.addProperty("comment", src.getComment());
      return result;
    }
  }

  public static class Deserializer implements JsonDeserializer<DeviceDto>
  {
    @Override
    public DeviceDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
      JsonObject jsonObject = json.getAsJsonObject();
      DeviceDto deviceDto = new DeviceDto();

      JsonElement element = jsonObject.get("brief");
      deviceDto.setBrief(element != null ? element.getAsString() : null);
      
      element = jsonObject.get("comment");
      deviceDto.setComment(element != null ? element.getAsString() : null);
      
      element = jsonObject.get("deviceId");
      deviceDto.setDeviceId(element != null ? element.getAsLong() : null);

      element = jsonObject.get("userId");
      deviceDto.setUser(element != null ? UserDao.INSTANCE.getUserInfo(element.getAsLong()) : null);
      
      return deviceDto;
    }
  }
}
