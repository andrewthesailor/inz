package com.szczepanika.scheduler.model.searchAlgorythms;

import com.szczepanika.scheduler.model.constrains.Constrains;
import com.szczepanika.scheduler.model.parameters.Room;
import com.szczepanika.scheduler.model.parameters.StudentGroup;
import com.szczepanika.scheduler.model.parameters.Subject;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import com.szczepanika.scheduler.model.variables.Exam;
import com.szczepanika.scheduler.model.variables.Timetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ForwardChecking {
    private List<Subject> subjects;
    private List<Room> rooms;
    private List<Timeslot> timeslots;
    private HashMap<StudentGroup, List<Timeslot>> studentGroupTimeDomain;

    public Timetable solve() {
        createDomains();
        return solveRecursive(new Timetable(),subjects);
    }

    private Timetable solveRecursive(Timetable timetable, List<Subject> subjectsToAssign) {
        if (subjectsToAssign.isEmpty() && Constrains.verifyConstrains(timetable))
            return timetable;
        for (Room roomToAssign : rooms) {
            List<Exam> exams = new ArrayList<>(List.copyOf(timetable.getExams()));
            for (Subject subjectToAssign : subjectsToAssign) {
                List<Timeslot> timeslots = getStudentGroupTimeDomain().get(subjectToAssign.getStudentGroup());
                for (Timeslot timeslotToAssign : timeslots) {
                    Exam exam = new Exam();
                    exam.setTimeslot(timeslotToAssign);
                    exam.setTeacher(subjectToAssign.getTeacher());
                    exam.setRoom(roomToAssign);
                    exam.setSubject(subjectToAssign);
                    exams.add(exam);
                    Timetable solution = new Timetable(exams);
                    if (Constrains.verifyConstrains(solution)) {
                        List<Timeslot> timeslotsLeft = List.copyOf(timeslots);
                        //Collections.copy(timeslotsLeft, timeslots);
                        clearDay(timeslots, timeslotToAssign.getDay());
                        List<Subject> subjectsLeft = new ArrayList<>(List.copyOf(subjectsToAssign));
                        subjectsLeft.remove(subjectToAssign);
                        if (Constrains.verifyTimeslotsForGroups(timeslotsLeft, subjectsLeft, subjectToAssign)) {
                            solution = solveRecursive(solution, subjectsLeft);
                            if (solution != null) {
                                return solution;
                            }
                        }
                    }
                    exams.remove(exam);
                }
            }
        }
        return null;
    }


    private void createDomains() {
        HashMap<StudentGroup, List<Timeslot>> domains = new HashMap<>();
        for (Subject subject : getSubjects()) {
            domains.computeIfAbsent(subject.getStudentGroup(), k -> new ArrayList<>(timeslots));
        }
        studentGroupTimeDomain = domains;
    }

    private void clearDay(List<Timeslot> timeslots, Integer day) {
        timeslots.removeIf(temp -> temp.getDay().equals(day));
    }
}
