package ru.pilot.tracks.point;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.TrackDao;
import ru.pilot.tracks.dto.PointDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointGet extends BaseCmd {
    private static final String ID_PARAM_NAME = "trackId";
    private static final String LIST_NAME = "pointList";

    @Override
    protected Map<String, Object> execute(HttpServletRequest req) {
        Long id = getIdParam(req, ID_PARAM_NAME);

        Map<String, Object> res = new HashMap<>();
        List<PointDto> resList = new ArrayList<>();
        if (id != null){
            resList.addAll(TrackDao.INSTANCE.getPointsByTrack(id));
        } 
        res.put(LIST_NAME, resList);
        return res;
    }
}