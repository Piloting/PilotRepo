package ru.pilot.tracks.device;

import com.google.gson.Gson;
import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dto.DeviceDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DeviceCreate extends BaseCmd {

    private static final String LIST_NAME = "deviceList";
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        /*List<DeviceDto> deviceList = getObjectFromJson(req);

        List<DeviceDto> deviceDtoList = DeviceDao.INSTANCE.createDevices(deviceList);
        print(deviceDtoList);

        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, deviceDtoList);
        return res;*/
        return null;
    }

    private List<DeviceDto> getObjectFromJson(HttpServletRequest req) throws IOException {
        DeviceDto[] userArray = new Gson().fromJson(req.getReader(), DeviceDto[].class);
        return new ArrayList<>(Arrays.asList(userArray));
    }
}
