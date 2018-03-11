package ru.pilot.tracks.point;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.TrackDao;
import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.TrackDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointCreate extends BaseCmd {

    private static final String LIST_NAME = "pointList";
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        List<PointDto> pointList = getObjectFromJson(req);

        Map<Long, List<PointDto>> trackToPointsMap = new HashMap<>();
        for (PointDto pointDto : pointList) {
            TrackDto track = pointDto.getTrack();
            List<PointDto> pointDtoList = trackToPointsMap.computeIfAbsent(track.getTrackId(), k -> new ArrayList<>());
            pointDtoList.add(pointDto);
        }
        
        for (Map.Entry<Long, List<PointDto>> entry : trackToPointsMap.entrySet()) {
             TrackDao.INSTANCE.addPoints(entry.getKey(), entry.getValue());
        }
        
        // todo не нужен выход
        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, pointList);
        return res;
    }

    private List<PointDto> getObjectFromJson(HttpServletRequest req) throws IOException {
        PointDto[] userArray = gson.fromJson(req.getReader(), PointDto[].class);
        return new ArrayList<>(Arrays.asList(userArray));
    }
}
