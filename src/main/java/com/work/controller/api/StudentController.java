package com.work.controller.api;

import com.work.pojo.Student;
import com.work.service.StudentService;
import com.work.service.impl.StudentServiceImpl;
import com.work.utils.FastJsonUtils;
import com.work.utils.Result;
import com.work.utils.ResultCodeEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "student", value = {
        "/api/student",
        "/api/student/save",
        "/api/student/del",
        "/api/student/update"
})
public class StudentController extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/"));

        System.out.println(target);

        Result result = Result.fail();

        switch (target){
            case  "/student":
                result = list(request);
                break;
            case "/save":
            case "/update":
                result = saveOrUpdate(request);
                break;
            case "/del":
                result = del(request);
                break;
        }

        FastJsonUtils.writeJson(result,response);
    }

    private Result del(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        studentService.deleteStudentById(id);
        return Result.ok().message("删除成功");

    }

    private Result saveOrUpdate(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String address = request.getParameter("address");

        Integer id = idStr == null ? null : Integer.parseInt(idStr);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setSex(sex);
        student.setAge(Integer.valueOf(age));
        student.setPhone(phone);
        student.setDepartment(department);
        student.setAddress(address);

        studentService.saveOrUpdate(student);

        return Result.ok().message("编辑成功");
    }

    private Result list(HttpServletRequest request){
        String type = request.getParameter("type");
        switch (type){
            case "all":
                List<Student> list = studentService.list();
                return Result.ok(list);
            case "searchById":
                String idStr = request.getParameter("id");
                Integer id=Integer.parseInt(idStr);
                Student student = studentService.getStudentById(id);
                return Result.ok(student);
            case "searchByName":
                String name = request.getParameter("name");
                List<Student> studentList = studentService.getStudentByName(name);
                return Result.ok(studentList);
        }
        return Result.fail().message("非法请求");
    }
}
