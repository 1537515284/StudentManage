package com.work.service.impl;

import com.work.dao.AdminDAO;
import com.work.pojo.Admin;
import com.work.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAO();

    @Override
    public Admin login(String username, String password) {
        return adminDAO.findAdminByUsernameAndPassword(username, password);
    }
}
