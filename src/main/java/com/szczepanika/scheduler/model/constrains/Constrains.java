package com.szczepanika.scheduler.model.constrains;

import com.szczepanika.scheduler.model.parameters.StudentGroup;
import com.szczepanika.scheduler.model.parameters.Subject;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import com.szczepanika.scheduler.model.variables.Exam;
import com.szczepanika.scheduler.model.variables.Timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Constrains {

    public static Boolean verifyConstrains(Timetable timetable) {
        if (verifyRooms(timetable)) {
            System.out.println("Rooms Verified");
            if (verifyLesson(timetable)) {
                System.out.println("Lessons verified");
                if(verifyTeachers(timetable)){
                    System.out.println("Teachers verified");
                    if(verifyStudents(timetable)){
                        System.out.println("students verified");
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //verify if room sizes are proper and if there are only one exam in given room at time
    private static Boolean verifyRooms(Timetable timetable) {
        for (Exam exam : timetable.getExams()) {
            if (!exam.checkRoomSize()) {
                System.out.println("room");
                return false;
            }
        }
        for (Timeslot timeslot : getExamMap(timetable).keySet()) {
            ArrayList<Exam> exams = getExamMap(timetable).get(timeslot);
            for (Exam exam : exams) {
                for (Exam toTest : exams) {
                    if (!exam.equals(toTest) && exam.getRoom().equals(toTest.getRoom())) {
                        System.out.println("room");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //verify if teachers have time for exams and if there is only one exam for teacher at time
    private static Boolean verifyTeachers(Timetable timetable) {
        for (Exam exam : timetable.getExams()) {
            if (!exam.checkTeacherAvailability()) {
                System.out.println("teacher");
                return false;
            }
        }
        for (Timeslot timeslot : getExamMap(timetable).keySet()) {
            ArrayList<Exam> exams = getExamMap(timetable).get(timeslot);
            for (Exam exam : exams) {
                for (Exam toTest : exams) {
                    if (!exam.equals(toTest) && exam.getTeacher().equals(toTest.getTeacher())) {
                        System.out.println("teacher");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //verify studentGroupConstrains
    private static Boolean verifyStudents(Timetable timetable) {
        HashMap<StudentGroup, ArrayList<Exam>> map = new HashMap<StudentGroup, ArrayList<Exam>>();
        for (Exam exam : timetable.getExams()) {
            map.computeIfAbsent(exam.getSubject().getStudentGroup(), k -> new ArrayList<Exam>());
            map.get(exam.getSubject().getStudentGroup()).add(exam);
        }
        for (StudentGroup group : map.keySet()) {
            ArrayList<Exam> groupExams = map.get(group);
            HashMap<Integer, Integer> groupPlan = new HashMap<>();
            for (Exam exam : groupExams) {
                if (groupPlan.get(exam.getTimeslot().getDay()) != null) {
                    System.out.println("student");
                    return false;
                }
                groupPlan.put(exam.getTimeslot().getDay(), 1);
            }
        }
        return true;
    }

    private static Boolean verifyLesson(Timetable timetable) {
        HashMap<Subject, Integer> table = new HashMap<>();
        for (Exam exam : timetable.getExams()) {
            if (table.get(exam.getSubject()) != null) {
                System.out.println("lesson");
                return false;
            }
            table.put(exam.getSubject(), 1);
        }
        return true;
    }

    private static HashMap<Timeslot, ArrayList<Exam>> getExamMap(Timetable timetable) {
        HashMap<Timeslot, ArrayList<Exam>> map = new HashMap<Timeslot, ArrayList<Exam>>();
        for (Exam exam : timetable.getExams()) {
            map.computeIfAbsent(exam.getTimeslot(), k -> new ArrayList<Exam>());
            map.get(exam.getTimeslot()).add(exam);
        }
        return map;
    }

    public static boolean verifyTimeslotsForGroups(List<Timeslot> timeslotsLeft, List<Subject> subjectsLeft, Subject subjectToAssign) {
        long subjectsInGroup = subjectsLeft.stream().filter(k->k.getStudentGroup().equals(subjectToAssign.getStudentGroup())).count();
        if(subjectsInGroup==0)
            return true;
        Map<Integer,Integer> dayCounter = new HashMap<>();
        for(Timeslot timeslot:timeslotsLeft){
            dayCounter.putIfAbsent(timeslot.getDay(), 1);
        }
        return subjectsInGroup < dayCounter.size();
    }
}
