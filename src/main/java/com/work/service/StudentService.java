package com.work.service;

import com.work.pojo.Student;

import java.util.List;

public interface StudentService {

    void saveOrUpdate(Student student);

    List<Student> list();

    Student getStudentById(Integer id);

    List<Student> getStudentByName(String name);

    void deleteStudentById(Integer id);
}
