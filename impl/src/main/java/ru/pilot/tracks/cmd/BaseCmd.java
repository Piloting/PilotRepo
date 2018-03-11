package ru.pilot.tracks.cmd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import ru.pilot.tracks.dto.BaseDto;
import ru.pilot.tracks.dto.DeviceDto;
import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.StatDto;
import ru.pilot.tracks.dto.TrackDto;
import ru.pilot.tracks.dto.UserDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public abstract class BaseCmd extends HttpServlet {
    protected Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(UserDto.class,   new UserDto.Serializer())
                .registerTypeHierarchyAdapter(DeviceDto.class, new DeviceDto.Serializer())
                .registerTypeHierarchyAdapter(DeviceDto.class, new DeviceDto.Deserializer())
                .registerTypeHierarchyAdapter(TrackDto.class,  new TrackDto.Serializer())
                .registerTypeHierarchyAdapter(TrackDto.class,  new TrackDto.Deserializer())
                .registerTypeHierarchyAdapter(StatDto.class,   new StatDto.Serializer())
                .registerTypeHierarchyAdapter(PointDto.class,  new PointDto.Serializer())
                .registerTypeHierarchyAdapter(PointDto.class,  new PointDto.Deserializer())
            .create();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getJson(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getJson(req, resp);
    }

    private void getJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> tdList = execute(req);

        String json = gson.toJson(tdList);
        
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
    
    protected Long getIdParam(HttpServletRequest req, String name){
        String id = req.getParameter(name);
        if (StringUtils.isNumeric(id)){
            return Long.parseLong(id);
        } 
        return null;
    }

    protected <T extends BaseDto> void print(List<T> objectList){
        for (T baseDto : objectList) {
            System.out.println(baseDto);
        }
    }
    
    protected abstract Map<String, Object> execute(HttpServletRequest req) throws IOException;
    
    

}
