package ru.pilot.tracks.device;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.DeviceDao;
import ru.pilot.tracks.dto.DeviceDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Example input: 
 [
     {
         "userId": 1002,
         "brief": "LG7",
         "comment": "commentLG7"
     }
 ]
 */
public class DeviceCreate extends BaseCmd {

    private static final String LIST_NAME = "deviceList";
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        List<DeviceDto> deviceList = getObjectFromJson(req);
       
        // clear IDs
        for (DeviceDto deviceDto : deviceList) {
            deviceDto.setDeviceId(null);
        }
        
        List<DeviceDto> deviceDtoList = DeviceDao.INSTANCE.createDevices(deviceList);

        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, deviceDtoList);
        return res;
    }

    private List<DeviceDto> getObjectFromJson(HttpServletRequest req) throws IOException {
        DeviceDto[] userArray = gson.fromJson(req.getReader(), DeviceDto[].class);
        return new ArrayList<>(Arrays.asList(userArray));
    }
}
