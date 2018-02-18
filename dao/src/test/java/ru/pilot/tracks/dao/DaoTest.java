package ru.pilot.tracks.dao;

import org.junit.Test;
import ru.pilot.tracks.dto.UserDto;

import javax.persistence.EntityManager;
import java.io.InputStream;
import java.util.List;

public class DaoTest {
    
    @Test
    public void userDaoTest(){
        UserDto userInfo = UserDao.INSTANCE.getUserInfo(1000L);
        System.out.println(userInfo);
        
        List<UserDto> allUsers = UserDao.INSTANCE.getAllUsers();
        for (UserDto user : allUsers) {
            System.out.println(user);
        }

        List<UserDto> pilot = UserDao.INSTANCE.getUserByLogin("pilot");
        System.out.println(pilot.toString());
    }
    
}
