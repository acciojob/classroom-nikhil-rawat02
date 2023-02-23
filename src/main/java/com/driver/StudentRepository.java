package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String,Student> studentMap;
    Map<String,Teacher> teacherMap;
    Map<String,List<String>> teacherStudentMap;
    StudentRepository(){
        studentMap = new HashMap<>();
        teacherMap = new HashMap<>();
        teacherStudentMap = new HashMap<>();
    }
    public void addStudent( Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair( String student, String teacher){
        List <String> list;
        if(teacherStudentMap.containsKey(teacher)){
            list= teacherStudentMap.get(teacher);
        }else list = new ArrayList<>();
        list.add(student);
        teacherStudentMap.put(teacher,list);
    }

    public Student getStudentByName(String name){
        return studentMap.get(name);
    }

    public Teacher getTeacherByName( String name){
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        return new ArrayList<>(teacherStudentMap.get(teacher));
    }

    public List<String> getAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName( String teacher){
        for(String student : teacherStudentMap.get(teacher)) {
                studentMap.remove(student);
        }
        teacherMap.remove(teacher);
        teacherStudentMap.remove(teacher);
    }

    public void deleteAllTeachers(){
        studentMap.clear();
        teacherMap.clear();
        teacherStudentMap.clear();
    }
}
