package com.change.mall.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.user.vo.User;

/**
 * 用户模块持久层代码:
 * 
 * 
 */
public class UserDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// 按名次查询是否有该用户:
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = getSession().createQuery(hql).setString(0, username).list();
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		}
		return null;
	}

	// 注册用户存入数据库代码实现
	public void save(User user) {
		getSession().save(user);
	}

	// 根据激活码查询用户
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = getSession().createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		}
		return null;
	}

	// 修改用户状态的方法
	public void update(User existUser) {
		getSession().update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state =?";

		List<User> list = getSession().createQuery(hql).setString(0, user.getUsername())
				.setString(1, user.getPassword()).setInteger(2, 1).list();
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		}
		return null;
	}

	// public int findCount() {
	// String hql = "select count(*) from User";
	// List<Long> list = this.getHibernateTemplate().find(hql);
	// if (list != null && list.size() > 0) {
	// return list.get(0).intValue();
	// }
	// return 0;
	// }
	//
	// public List<User> findByPage(int begin, int limit) {
	// String hql = "from User";
	// List<User> list = this.getHibernateTemplate().execute(
	// new PageHibernateCallback<User>(hql, null, begin, limit));
	// return list;
	// }
	//
	// public User findByUid(Integer uid) {
	// return this.getHibernateTemplate().get(User.class, uid);
	// }
	//
	// public void delete(User existUser) {
	// this.getHibernateTemplate().delete(existUser);
	// }
}
