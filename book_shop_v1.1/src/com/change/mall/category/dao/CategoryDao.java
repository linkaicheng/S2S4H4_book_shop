package com.change.mall.category.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.category.vo.Category;

/**
 * 一级分类的持久层对象
 *
 */
public class CategoryDao {
	private SessionFactory sessionFactory;

	// DAO层的查询所有一级分类的方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Category> findAll() {
		String hql = "from Category";
		Query query = getSession().createQuery(hql);
		List<Category> list = query.list();
		// for (Category category : list) {
		// System.out.println(category.getCname());
		// }
		return list;
	}

	// Dao中的保存一级分类的方法
	public void save(Category category) {
		getSession().save(category);
	}

	// Dao中根据一级分类id查询一级分类
	public Category findByCid(Integer cid) {
		String hql = "from Category where cid=?";
		Query query = getSession().createQuery(hql).setInteger(0, cid);
		Category category = (Category) query.uniqueResult();
		return category;
	}

	// DAO中删除一级分类
	public void delete(Category category) {
		getSession().delete(category);
	}

	// Dao中修改一级分类
	public void update(Category category) {
		getSession().update(category);
	}

}
