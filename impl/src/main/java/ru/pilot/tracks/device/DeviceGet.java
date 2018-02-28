package ru.pilot.tracks.device;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.DeviceDao;
import ru.pilot.tracks.dto.DeviceDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceGet extends BaseCmd {

    private static final String ID_PARAM_NAME = "deviceId";
    private static final String USER_ID_PARAM_NAME = "userId";
    private static final String LIST_NAME = "deviceList";

    @Override
    protected Map<String, Object> execute(HttpServletRequest req) {
        Long id = getIdParam(req, ID_PARAM_NAME);
        Long userId = getIdParam(req, USER_ID_PARAM_NAME);

        Map<String, Object> res = new HashMap<>();
        List<DeviceDto> resList = new ArrayList<>();
        if (id != null){
            resList.add(DeviceDao.INSTANCE.getDeviceInfo(id));
        } else if (userId != null) {
            resList.addAll(DeviceDao.INSTANCE.getDevicesByUser(userId));
        } else {
            resList.addAll(DeviceDao.INSTANCE.getAllDevice());
        }
        res.put(LIST_NAME, resList);
        return res;
    }
}