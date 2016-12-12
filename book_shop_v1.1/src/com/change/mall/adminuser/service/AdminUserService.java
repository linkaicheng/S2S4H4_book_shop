package com.change.mall.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.change.mall.adminuser.dao.AdminUserDao;
import com.change.mall.adminuser.vo.AdminUser;
@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
