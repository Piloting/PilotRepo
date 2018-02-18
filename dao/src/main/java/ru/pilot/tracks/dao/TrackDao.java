package ru.pilot.tracks.dao;

import java.util.Date;
import java.util.List;

public class TrackDao {

    public TrackDao INSTANCE = new TrackDao();
    private TrackDao (){}

    public List<Object> getTracksByUser(Long userId){
        return null;
    }
    public List<Object> getTracksByUserAndDate(Long userId, Date startDate, Date endDate){
        return null;
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
