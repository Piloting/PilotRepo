package ru.pilot.tracks.dao;

import ru.pilot.tracks.dto.DeviceDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DeviceDao extends BaseDao{
    public static DeviceDao INSTANCE = new DeviceDao();
    private DeviceDao(){}
    
    public DeviceDto getDeviceInfo(Long deviceId){
        return session.load(DeviceDto.class, deviceId);
    }
    
    public List<DeviceDto> getAllDevice(){
        CriteriaQuery<DeviceDto> query = cb.createQuery(DeviceDto.class);
        query.select(query.from(DeviceDto.class));
        return session.createQuery(query).getResultList();
    }
    
    public List<DeviceDto> getDevicesByUser(Long userId){
        CriteriaQuery<DeviceDto> query = cb.createQuery(DeviceDto.class);
        Root<DeviceDto> devices = query.from(DeviceDto.class);
        query.select(devices);
        query.where(cb.equal(devices.get("user"),userId));
        return session.createQuery(query).getResultList();
    }

}
