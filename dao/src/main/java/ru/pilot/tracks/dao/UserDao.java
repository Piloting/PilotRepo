package ru.pilot.tracks.dao;

import org.hibernate.Session;
import ru.pilot.tracks.dto.UserDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao extends BaseDao{
    public static UserDao INSTANCE = new UserDao();
    private UserDao (){}
    
    public Long createUser(String login, String brief, String name, String pass){
        Session session = newSession();
        session.beginTransaction();
        
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setBrief(brief);
        userDto.setName(name);
        userDto.setPass(pass);
        session.save(userDto);
        
        session.getTransaction().commit();
        
        return userDto.getUserId();
    }
    
    public UserDto getUserInfo(Long userId){
        return newSession().load(UserDto.class, userId);
    }
    
    public List<UserDto> getAllUsers(){
        CriteriaQuery<UserDto> query = cb.createQuery(UserDto.class);
        query.select(query.from(UserDto.class));
        return newSession().createQuery(query).getResultList();
    }
    
    public List<UserDto> getUserByLogin(String login){
        CriteriaQuery<UserDto> query = cb.createQuery(UserDto.class);
        Root<UserDto> users = query.from(UserDto.class);
        query.select(users);
        query.where(cb.equal(users.get("login"),login));
        return newSession().createQuery(query).getResultList();
    }

}
