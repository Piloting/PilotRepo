package ru.pilot.tracks.idProvider;

public interface IdProvider {
    String TABLE = "tSequence";
    String PK_COLUMN = "table_name";
    String VALUE_COLUMN = "next_id";
}