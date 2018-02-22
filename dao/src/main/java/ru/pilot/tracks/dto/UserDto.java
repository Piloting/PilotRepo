package ru.pilot.tracks.dto;


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
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = UserDto.TABLE_NAME)
@TableGenerator(
        table = IdProvider.TABLE,
        pkColumnName = IdProvider.PK_COLUMN,
        valueColumnName = IdProvider.VALUE_COLUMN,
        name = UserDto.TABLE_NAME,
        pkColumnValue = UserDto.TABLE_NAME
)
public class UserDto implements Serializable {
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
    private List<DeviceDto> deviceDtoList;
    
    public List<DeviceDto> getDeviceDtoList() { return deviceDtoList; }
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
        return "User[id="+userId+
                ", login="+login+
                ", brief="+brief+
                ", name="+name+
                ", pass="+pass+
                "]";
    }
}
