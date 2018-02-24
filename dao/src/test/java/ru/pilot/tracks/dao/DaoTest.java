package ru.pilot.tracks.dao;

import org.junit.Test;
import ru.pilot.tracks.dto.DeviceDto;
import ru.pilot.tracks.dto.PointDto;
import ru.pilot.tracks.dto.TrackDto;
import ru.pilot.tracks.dto.UserDto;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DaoTest {
    
    @Test
    public void userDaoTest(){
        UserDto userInfo = UserDao.INSTANCE.getUserInfo(1000L);
        System.out.println(userInfo);
        
        List<UserDto> allUsers = UserDao.INSTANCE.getAllUsers();
        printAllObject();

        List<UserDto> pilot = UserDao.INSTANCE.getUserByLogin("pilot");
        System.out.println(pilot.toString());

        UserDto user = UserDao.INSTANCE.createUser("Love", "Love", "Love", "qwasvvvvvt342l!@3");
        System.out.println(user);
    }

    private void printAllObject() {
        List<UserDto> allUsers = UserDao.INSTANCE.getAllUsers();
        for (UserDto user : allUsers) {
            System.out.println(user);
            for (DeviceDto deviceDto : user.getDeviceDtoList()) {
                System.out.println("   " + deviceDto);
                for (TrackDto trackDto : deviceDto.getTrackDtoList()) {
                    System.out.println("      " + trackDto);
                    for (PointDto pointDto : trackDto.getPointDtoList()) {
                        System.out.println("         " + pointDto);
                    }
                }
            }
            System.out.println();
        }
    }

    @Test
    public void deviceDaoTest(){
        List<DeviceDto> allDevice = DeviceDao.INSTANCE.getAllDevice();
        for (DeviceDto device : allDevice) {
            System.out.println(device);
        }

        DeviceDto deviceInfo = DeviceDao.INSTANCE.getDeviceInfo(1000L);
        System.out.println(deviceInfo);

        List<DeviceDto> devicesByUser = DeviceDao.INSTANCE.getDevicesByUser(1000L);
        System.out.println(devicesByUser);

        DeviceDto device = DeviceDao.INSTANCE.createDevice(1000L, "NewMobile", null);
        System.out.println(device);

        DeviceDao.INSTANCE.updateDevice(device.getDeviceId(), 1001L, null, "com");
    }
    
    @Test
    public void trackDaoTest(){
        List<TrackDto> tracksByDevice = TrackDao.INSTANCE.getTracksByDevice(1000L);
        for (TrackDto track : tracksByDevice) {
            System.out.println(track);
        }
        
        List<TrackDto> tracksByDeviceAndDate = TrackDao.INSTANCE.getTracksByDeviceAndDate(
                        1000L, 
                        getStartDate(2018, 2, 19),
                        getEndDate(2018, 2, 19));
        for (TrackDto track : tracksByDeviceAndDate) {
            System.out.println(track);
        }

        List<PointDto> pointsByTrack = TrackDao.INSTANCE.getPointsByTrack(1000L);
        for (PointDto point : pointsByTrack) {
            System.out.println(point);
        }
    }


    @Test
    public void complexTest(){
        UserDto user = UserDao.INSTANCE.createUser("TestLogin", "TestBrief", "TestName", "TestPass");

        DeviceDto device = DeviceDao.INSTANCE.createDevice(user.getUserId(), "TestDevice", "testComment");

        TrackDto track = TrackDao.INSTANCE.createTrack(device.getDeviceId(), "TestTrack", "TestComment", new Date(), null);
        
        PointDto pointDto = new PointDto(track, new Date(), new BigDecimal(100), new BigDecimal(100), new BigDecimal(100), new BigDecimal(100));
        TrackDao.INSTANCE.addPoint(track, pointDto);
        TrackDao.INSTANCE.addPoints(track, createPoints(track, 100));
        
        printAllObject();
        
        UserDao.INSTANCE.deleteUser(user.getUserId());
        System.out.println("------------------------------------------");
        printAllObject();
    }
    
    private List<PointDto> createPoints(TrackDto trackDto, Integer count){
        Instant instant = Instant.now();
        
        List<PointDto> points = new ArrayList<>(count);
        for (long i = 0; i<count; i++){
            PointDto pointDto = new PointDto(trackDto, Date.from(instant.plusSeconds(i)), 
                    new BigDecimal(100), 
                    new BigDecimal(100), 
                    new BigDecimal(100), 
                    new BigDecimal(60));
            points.add(pointDto);
        }
        return points;
    }
    
    private Date getStartDate(Integer yyyy, Integer mm, Integer dd){
        LocalDateTime specificDate = LocalDateTime.of(yyyy, Month.of(mm), dd, 0, 0);
        return Date.from(specificDate.toInstant(ZoneOffset.UTC));
    }
    private Date getEndDate(Integer yyyy, Integer mm, Integer dd){
        LocalDateTime specificDate = LocalDateTime.of(yyyy, Month.of(mm), dd, 23, 59, 59);
        return Date.from(specificDate.toInstant(ZoneOffset.UTC));
    }
    
}
