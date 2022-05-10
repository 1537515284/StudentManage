package com.work.dao;

import com.work.pojo.Student;

import java.util.List;

public class StudentDAO extends BasicDAO<Student> {

    public void insert(Student student){
        String sql = "INSERT INTO student(name,sex,age,phone,department,address) VALUES(?,?,?,?,?,?);";
        this.update(sql, student.getName(), student.getSex(), student.getAge(), student.getPhone(), student.getDepartment(), student.getAddress());
    }

    public void updateById(Student student){
        String sql = "UPDATE student SET `name` = ?,sex = ?,age = ?, phone=?,department=?,address=? WHERE id = ?;";
        this.update(sql,student.getName(), student.getSex(), student.getAge(), student.getPhone(), student.getDepartment(), student.getAddress(),student.getId());
    }

    public List<Student> findAll(){
        String sql = "SELECT id,name,sex,age,phone,department,address FROM student";
        return this.queryMulti(sql,Student.class);
    }

    public Student findById(Integer id){
        String sql = "SELECT id,name,sex,age,phone,department,address FROM student WHERE id = ?";
        return this.querySingle(sql,Student.class,id);
    }

    public List<Student> findByName(String name) {
        String sql = "SELECT id,name,sex,age,phone,department,address FROM student WHERE name like ?";
        return this.queryMulti(sql,Student.class,"%"+name+"%");
    }

    public void deleteById(Integer id){
        String sql ="DELETE FROM student WHERE id = ?";
        this.update(sql,id);
    }
}
