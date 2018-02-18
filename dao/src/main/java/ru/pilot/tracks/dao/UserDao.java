package ru.pilot.tracks.dao;

import ru.pilot.tracks.dto.UserDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao extends BaseDao{
    public static UserDao INSTANCE = new UserDao();
    private UserDao (){}
    
    public Long createUser(){
        return null;
    }
    
    public UserDto getUserInfo(Long userId){
        return session.load(UserDto.class, 1000L);
    }
    
    public List<UserDto> getAllUsers(){
        CriteriaQuery<UserDto> query = criteriaBuilder.createQuery(UserDto.class);
        query.select(query.from(UserDto.class));
        return session.createQuery(query).getResultList();
    }
    public List<UserDto> getUserByLogin(String login){
        CriteriaQuery<UserDto> query = criteriaBuilder.createQuery(UserDto.class);
        Root<UserDto> users = query.from(UserDto.class);
        query.select(users);
        query.where(criteriaBuilder.equal(users.get("login"),login));
        return session.createQuery(query).getResultList();
    }

}
