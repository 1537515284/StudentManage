package com.work.service;

import com.work.pojo.Admin;

public interface AdminService {
    Admin login(String username, String password);
}
