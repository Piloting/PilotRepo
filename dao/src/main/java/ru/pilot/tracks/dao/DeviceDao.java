package ru.pilot.tracks.dao;

import org.hibernate.Session;
import ru.pilot.tracks.dto.DeviceDto;
import ru.pilot.tracks.dto.UserDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DeviceDao extends BaseDao{
    public static DeviceDao INSTANCE = new DeviceDao();
    private DeviceDao(){}
    
    public Long createDevice(Long userId, String brief, String comment){
        Session session = newSession();
        session.beginTransaction();

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setUser(session.load(UserDto.class, userId));
        deviceDto.setBrief(brief);
        deviceDto.setComment(comment);
        session.save(deviceDto);

        session.getTransaction().commit();
        return deviceDto.getDeviceId();
    }
    
    public Long updateDevice(Long deviceId, Long userId, String brief, String comment){
        Session session = newSession();
        session.beginTransaction();

        DeviceDto deviceDto = getDeviceInfo(deviceId);
        deviceDto.setUser(session.load(UserDto.class, userId));
        deviceDto.setBrief(brief);
        deviceDto.setComment(comment);
        session.save(deviceDto);

        session.getTransaction().commit();
        return deviceDto.getDeviceId();
    }
    
    public DeviceDto getDeviceInfo(Long deviceId){
        return newSession().load(DeviceDto.class, deviceId);
    }
    
    public List<DeviceDto> getAllDevice(){
        CriteriaQuery<DeviceDto> query = cb.createQuery(DeviceDto.class);
        query.select(query.from(DeviceDto.class));
        return newSession().createQuery(query).getResultList();
    }
    
    public List<DeviceDto> getDevicesByUser(Long userId){
        CriteriaQuery<DeviceDto> query = cb.createQuery(DeviceDto.class);
        Root<DeviceDto> devices = query.from(DeviceDto.class);
        query.select(devices);
        query.where(cb.equal(devices.get("user"),userId));
        return newSession().createQuery(query).getResultList();
    }

}
