package ru.pilot.tracks.cmd;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import ru.pilot.tracks.dto.BaseDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public abstract class BaseCmd extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getJson(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getJson(req, resp);
    }

    private void getJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        Map<String, Object> tdList = execute(req);
        
        String json = new Gson().toJson(tdList);
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
