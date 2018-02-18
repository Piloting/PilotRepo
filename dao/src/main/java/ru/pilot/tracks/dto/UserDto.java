package ru.pilot.tracks.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tuser")
public class UserDto {
    @Id
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
