package com.szczepanika.scheduler.model.variables;


import com.szczepanika.scheduler.model.parameters.Room;
import com.szczepanika.scheduler.model.parameters.Subject;
import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Exam {
    private Timeslot timeslot;
    private Subject subject;
    private Teacher teacher;
    private Room room;

    public Boolean checkRoomSize(){
        return getSubject().getStudentCount() <= getRoom().getSize();
    }

    public Boolean checkTeacherAvailability(){
        return getTeacher().getAvailableTimeslots().contains(timeslot);
    }


}
