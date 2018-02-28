package ru.pilot.tracks.track;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.TrackDao;
import ru.pilot.tracks.dto.TrackDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackGet extends BaseCmd {

    private static final String ID_PARAM_NAME = "trackId";
    private static final String DEVICE_ID_PARAM_NAME = "deviceId";
    private static final String LIST_NAME = "trackList";

    @Override
    protected Map<String, Object> execute(HttpServletRequest req) {
        Long id = getIdParam(req, ID_PARAM_NAME);
        Long deviceId = getIdParam(req, DEVICE_ID_PARAM_NAME);

        Map<String, Object> res = new HashMap<>();
        List<TrackDto> resList = new ArrayList<>();
        if (id != null){
            resList.add(TrackDao.INSTANCE.getTrackInfo(id));
        } else if (deviceId != null) {
            resList.addAll(TrackDao.INSTANCE.getTracksByDevice(deviceId));
        } else {
            resList.addAll(TrackDao.INSTANCE.getAllTrack());
        }
        res.put(LIST_NAME, resList);
        return res;
    }
}