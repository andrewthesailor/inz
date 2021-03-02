package com.szczepanika.scheduler.utils;

import com.szczepanika.scheduler.model.parameters.Room;
import com.szczepanika.scheduler.model.parameters.Timeslot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class InitUtils {
    public static HashMap<Room, ArrayList<Timeslot>>getRoomTimeslotCombination(List<Room>rooms, List<Timeslot>timeslots){
        HashMap<Room, ArrayList<Timeslot>>combination = new HashMap<>();
        for(Room room: rooms){
            combination.put(room, (ArrayList<Timeslot>) timeslots);
        }
        return combination;
    }


}
