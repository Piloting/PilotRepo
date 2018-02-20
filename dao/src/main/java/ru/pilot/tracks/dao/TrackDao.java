package ru.pilot.tracks.dao;

import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.TrackDto;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class TrackDao extends BaseDao {
    public static TrackDao INSTANCE = new TrackDao();
    private TrackDao (){}

    
    public List<TrackDto> getTracksByDevice(Long deviceId){
        CriteriaQuery<TrackDto> query = cb.createQuery(TrackDto.class);
        Root<TrackDto> tracks = query.from(TrackDto.class);
        query.select(tracks);
        query.where(cb.equal(tracks.get("device"),deviceId));
        return session.createQuery(query).getResultList();
    }
    
    public List<TrackDto> getTracksByDeviceAndDate(Long deviceId, Date startDate, Date endDate){
        CriteriaQuery<TrackDto> query = cb.createQuery(TrackDto.class);
        Root<TrackDto> tracks = query.from(TrackDto.class);
        query.select(tracks);
        query.where(cb.and(
                cb.equal(tracks.get("device"),deviceId), 
                cb.between(tracks.get("dateStart"),startDate, endDate))
        );
        return session.createQuery(query).getResultList();
    }
    
    public List<PointDto> getPointsByTrack(Long trackId){
        CriteriaQuery<PointDto> query = cb.createQuery(PointDto.class);
        Root<PointDto> points = query.from(PointDto.class);
        query.select(points);
        query.where(cb.equal(points.get("track"),trackId));
        return session.createQuery(query).getResultList();
    }
    
    

    //////////////////////////
    public Long createTrack(Long deviceId, String name, Date startDate, Date endDate){
        return 1L;
    }
    public Long createTrack(Long deviceId, String name, Date startDate){
        return 1L;
    }
    public void finalizeTrack(Long trackId, Date endDate){

    }
    public void putPoints(Long trackId, List<Object> pointList){

    }
    public Long createStatistic(Long trackId, String name){
        return 1L;
    }
}
