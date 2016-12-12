package com.change.mall.categorysecond.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.categorysecond.vo.CategorySecond;

/**
 * 二级分类的Dao层的代码
 * 
 * 
 */
public class CategorySecondDao {
	private SessionFactory sessionFactory;

	// DAO中的统计二级分类个数的方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = getSession().createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return (list.get(0)).intValue();
		}
		return 0;
	}

	// DAO中分页查询的方法
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		// List<CategorySecond> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<CategorySecond>(hql, null, begin,
		// limit));
		Query query = getSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit);
		List<CategorySecond> list = query.list();
		return list;
	}

	// DAO中的保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		getSession().save(categorySecond);
	}

	// DAO中的删除二级分类的方法
	public void delete(CategorySecond categorySecond) {
		getSession().delete(categorySecond);
	}

	// DAO中根据id查询二级分类的方法
	public CategorySecond findByCsid(Integer csid) {
		String hql = "from CategorySecond where csid=?";
		Query query = getSession().createQuery(hql).setInteger(0, csid);
		return (CategorySecond) query.uniqueResult();
	}

	// DAO中的修改二级分类的方法
	public void update(CategorySecond categorySecond) {
		getSession().update(categorySecond);
	}

	// DAO中的查询所有二级分类的方法
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return getSession().createQuery(hql).list();
	}

}
