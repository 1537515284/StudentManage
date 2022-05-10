package com.work.dao;

import com.work.pojo.Admin;

public class AdminDAO extends BasicDAO<Admin>{


    public Admin findAdminByUsernameAndPassword(String username,String password){
        String sql = "SELECT id,username,password FROM admin WHERE username = ? AND password = ?";
        return this.querySingle(sql, Admin.class, username, password);
    }
}
