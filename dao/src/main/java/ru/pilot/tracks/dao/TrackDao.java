package ru.pilot.tracks.dao;

import org.hibernate.Session;
import ru.pilot.tracks.dto.DeviceDto;
import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.TrackDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TrackDao extends BaseDao {
    public static TrackDao INSTANCE = new TrackDao();
    private TrackDao (){}

    public List<TrackDto> getAllTrack(){
        CriteriaQuery<TrackDto> query = cb.createQuery(TrackDto.class);
        query.select(query.from(TrackDto.class));
        return newSession().createQuery(query).getResultList();
    }
    
    public TrackDto getTrackInfo(Serializable trackId){
        return newSession().load(TrackDto.class, trackId);
    }
    
    public List<TrackDto> getTracksByDevice(Long deviceId){
        CriteriaQuery<TrackDto> query = cb.createQuery(TrackDto.class);
        Root<TrackDto> tracks = query.from(TrackDto.class);
        query.select(tracks);
        query.where(cb.equal(tracks.get("device"),deviceId));
        return newSession().createQuery(query).getResultList();
    }
    
    public List<TrackDto> getTracksByDeviceAndDate(Long deviceId, Date startDate, Date endDate){
        CriteriaQuery<TrackDto> query = cb.createQuery(TrackDto.class);
        Root<TrackDto> tracks = query.from(TrackDto.class);
        query.select(tracks);
        query.where(cb.and(
                cb.equal(tracks.get("device"),deviceId), 
                cb.between(tracks.get("dateStart"),startDate, endDate))
        );
        return newSession().createQuery(query).getResultList();
    }
    
    public List<PointDto> getPointsByTrack(Long trackId){
        CriteriaQuery<PointDto> query = cb.createQuery(PointDto.class);
        Root<PointDto> points = query.from(PointDto.class);
        query.select(points);
        query.where(cb.equal(points.get("track"),trackId));
        return newSession().createQuery(query).getResultList();
    }
    
    public TrackDto createTrack(Long deviceId, String name, String comment, Date dateStart, Date dateEnd){
        Session session = newSession();
        session.beginTransaction();

        TrackDto trackDto = new TrackDto();
        DeviceDto deviceDto = session.load(DeviceDto.class, deviceId);
        trackDto.setDevice(deviceDto);
        trackDto.setName(name);
        trackDto.setComment(comment);
        trackDto.setDateStart(dateStart);
        trackDto.setDateEnd(dateEnd);
        deviceDto.getTrackDtoList().add(trackDto);
        session.save(trackDto);
        session.save(deviceDto);

        session.getTransaction().commit();
        return trackDto;
    }
    
    public List<TrackDto> createTracks(List<TrackDto> deviceList){
        Session session = newSession();
        session.beginTransaction();

        for (TrackDto trackDto : deviceList) {
            DeviceDto deviceDto = session.load(DeviceDto.class, trackDto.getDevice().getDeviceId());
            deviceDto.getTrackDtoList().add(trackDto);
            session.save(trackDto);
            session.save(deviceDto);
        }

        session.getTransaction().commit();
        return deviceList;
    }
    
    public Long updateTrack(Long trackId, Long deviceId, String name, String comment, Date dateStart, Date dateEnd){
        Session session = newSession();
        session.beginTransaction();

        TrackDto trackDto = session.load(TrackDto.class, trackId);
        trackDto.setDevice(session.load(DeviceDto.class, deviceId));
        trackDto.setName(name);
        trackDto.setComment(comment);
        trackDto.setDateStart(dateStart);
        trackDto.setDateEnd(dateEnd);
        session.save(trackDto);

        session.getTransaction().commit();
        return trackDto.getTrackId();
    }

    public void deleteTrack(Long trackId){
        delete(TrackDto.class, trackId);
    }

    public void addPoint(TrackDto trackDto, PointDto pointDto){
        Session session = newSession();
        session.beginTransaction();

        trackDto.getPointDtoList().add(pointDto);
        session.save(pointDto);
        session.save(trackDto);

        session.getTransaction().commit();
    }
    
    public void addPoints(TrackDto trackDto, List<PointDto> pointDtoList){
        Session session = newSession();
        session.beginTransaction();

        for (PointDto pointDto : pointDtoList) {
            session.save(pointDto);
        }
        trackDto.getPointDtoList().addAll(pointDtoList);
        session.save(trackDto);

        session.getTransaction().commit();
    }
    
    public void addPoints(Long trackId, List<PointDto> pointDtoList){
        TrackDto trackInfo = getTrackInfo(trackId);
        addPoints(trackInfo, pointDtoList);
    }

    //////////////////////////
    public void finalizeTrack(Long trackId, Date endDate){

    }
    public void putPoints(Long trackId, List<Object> pointList){

    }
    public Long createStatistic(Long trackId, String name){
        return 1L;
    }
}
