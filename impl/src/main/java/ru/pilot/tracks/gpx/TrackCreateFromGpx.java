package ru.pilot.tracks.gpx;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.DeviceDao;
import ru.pilot.tracks.dao.TrackDao;
import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.TrackDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// todo 
@MultipartConfig
public class TrackCreateFromGpx extends BaseCmd {

    private static final String ID_PARAM_NAME = "deviceId";
    private static final String LIST_NAME = "trackList";
    
    @Override
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        List<TrackDto> trackList = new ArrayList<>();
        try {
            Long deviceId = getIdParam(req, ID_PARAM_NAME);
            Part filePart = req.getPart("gpxFile");
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

            GPX gpx = GPX.read( filePart.getInputStream());

            for (Track track : gpx.getTracks()) {
                TrackDto trackDto = new TrackDto();
                trackDto.setDevice(DeviceDao.INSTANCE.getDeviceInfo(deviceId));
                track.getName().ifPresent(trackDto::setName);
                track.getComment().ifPresent(trackDto::setComment);
                TrackDao.INSTANCE.createTracks(Collections.singletonList(trackDto));

                List<PointDto> pointList = new ArrayList<>();
                for (TrackSegment segment : track.getSegments()) {
                    for (WayPoint point : segment.getPoints()) {
                        PointDto pointDto = new PointDto();
                        if (point.getTime().isPresent()){
                            pointDto.setDate(Date.from(point.getTime().get().toInstant()));
                        }
                        pointDto.setX(BigDecimal.valueOf(point.getLatitude().doubleValue()));
                        pointDto.setY(BigDecimal.valueOf(point.getLongitude().doubleValue()));
                        if (point.getElevation().isPresent()){
                            pointDto.setZ(BigDecimal.valueOf(point.getElevation().get().doubleValue()));
                        }
                        if (point.getSpeed().isPresent()){
                            pointDto.setSpeed(BigDecimal.valueOf(point.getSpeed().get().doubleValue())); // todo unit m/s
                        }
                        pointList.add(pointDto);
                    }
                }
                
                TrackDao.INSTANCE.addPoints(trackDto, pointList);
                trackList.add(trackDto);
            }
            
        } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, trackList);
        return res;
    }
}
