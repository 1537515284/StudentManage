package com.work.service.impl;

import com.work.dao.StudentDAO;
import com.work.pojo.Student;
import com.work.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    public void saveOrUpdate(Student student) {
        if(student.getId() == null)
            studentDAO.insert(student);
        else
            studentDAO.updateById(student);
    }

    @Override
    public List<Student> list() {
        return studentDAO.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentDAO.deleteById(id);
    }
}
