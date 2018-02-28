package ru.pilot.tracks.user;

import ru.pilot.tracks.cmd.BaseCmd;
import ru.pilot.tracks.dao.UserDao;
import ru.pilot.tracks.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGet extends BaseCmd {

    private static final String ID_PARAM_NAME = "userId";
    private static final String LIST_NAME = "userList";

    @Override
    protected Map<String, Object> execute(HttpServletRequest req) {
        Long id = getIdParam(req, ID_PARAM_NAME);
        
        Map<String, Object> res = new HashMap<>();
        List<UserDto> resList = new ArrayList<>();
        if (id != null){
            resList.add(UserDao.INSTANCE.getUserInfo(id));
        } else {
            resList.addAll(UserDao.INSTANCE.getAllUsers());
        }
        res.put(LIST_NAME, resList);
        return res;
    }
}