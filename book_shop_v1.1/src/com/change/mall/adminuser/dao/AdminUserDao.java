package com.change.mall.adminuser.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.adminuser.vo.AdminUser;

public class AdminUserDao {
	private SessionFactory sessionFactory;

	// Dao完成登录的代码
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		Query query = getSession().createQuery(hql).setString(0, adminUser.getUsername()).setString(1,
				adminUser.getPassword());

		List<AdminUser> list = query.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
