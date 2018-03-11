package ru.pilot.tracks.user;

import com.google.gson.Gson;
import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.UserDao;
import ru.pilot.tracks.dto.UserDto;

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
         "login": "Pilot332",
         "brief": "Pilot",
         "name": "Pilot Pilot"
     },
     {
         "login": "Victorwetew",
         "brief": "Victor",
         "name": "Victor Victor"
     },
     {
         "login": "Igoergergerg",
         "brief": "Igor",
         "name": "Igor Igor"
     }
 ]
 */
    
public class UserCreate extends BaseCmd {

    private static final String LIST_NAME = "userList";
    protected Map<String, Object> execute(HttpServletRequest req) throws IOException {
        List<UserDto> userList = getObjectFromJson(req);
        
        List<UserDto> userDtoList = UserDao.INSTANCE.createUsers(userList);
        print(userDtoList);
        
        Map<String, Object> res = new HashMap<>();
        res.put(LIST_NAME, userDtoList);
        return res;
    }

    private List<UserDto> getObjectFromJson(HttpServletRequest req) throws IOException {
        UserDto[] userArray = new Gson().fromJson(req.getReader(), UserDto[].class);
        return new ArrayList<>(Arrays.asList(userArray));
    }
}
