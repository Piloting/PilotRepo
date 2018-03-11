package ru.pilot.tracks.dto;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.pilot.tracks.idProvider.IdProvider;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = UserDto.TABLE_NAME)
@TableGenerator(
        table = IdProvider.TABLE,
        pkColumnName = IdProvider.PK_COLUMN,
        valueColumnName = IdProvider.VALUE_COLUMN,
        name = UserDto.TABLE_NAME,
        pkColumnValue = UserDto.TABLE_NAME,
        allocationSize = IdProvider.BATCH_SIZE
)
public class UserDto extends BaseDto {
    static final String TABLE_NAME = "tUser";
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator=UserDto.TABLE_NAME)
    @Column(name = "userId")
    private Long userId;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "brief")
    private String brief;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "pass")
    private String pass;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    transient private List<DeviceDto> deviceDtoList;
    
    public List<DeviceDto> getDeviceDtoList() { 
        if (deviceDtoList == null){
            deviceDtoList = new ArrayList<>();
        }
        return deviceDtoList; 
    }
    public void setDeviceDtoList(List<DeviceDto> deviceDtoList) { this.deviceDtoList = deviceDtoList; }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User[userId="+userId+
                ", login="+login+
                ", brief="+brief+
                ", name="+name+
                ", pass="+pass+
                "]";
    }

    public static class Serializer implements JsonSerializer<UserDto> {
        @Override
        public JsonElement serialize(UserDto src, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject result = new JsonObject();
            result.addProperty("userId", src.getUserId());
            result.addProperty("login", src.getLogin());
            result.addProperty("brief", src.getBrief());
            result.addProperty("name", src.getName());
            result.addProperty("pass", src.getPass());
            return result;
        }
    }
}
