package ru.pilot.tracks.track;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.TrackDao;
import ru.pilot.tracks.dto.TrackDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackCreate extends BaseCmd {

    private static final String LIST_NAME = "trackList";
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        List<TrackDto> trackList = getObjectFromJson(req);

        // clear IDs
        for (TrackDto trackDto : trackList) {
            trackDto.setTrackId(null);
        }

        List<TrackDto> trackDtoList = TrackDao.INSTANCE.createTracks(trackList);

        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, trackDtoList);
        return res;
    }

    private List<TrackDto> getObjectFromJson(HttpServletRequest req) throws IOException {
        TrackDto[] userArray = gson.fromJson(req.getReader(), TrackDto[].class);
        return new ArrayList<>(Arrays.asList(userArray));
    }
}
