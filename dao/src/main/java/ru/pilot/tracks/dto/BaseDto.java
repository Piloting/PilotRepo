package ru.pilot.tracks.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BaseDto implements Serializable {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.X");
    protected static Date strToDate(String date){
        ZonedDateTime dateTime = ZonedDateTime.parse(date, formatter);
        return Date.from(dateTime.toInstant());
    }
}
