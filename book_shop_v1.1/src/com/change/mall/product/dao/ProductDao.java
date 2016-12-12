package com.change.mall.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.product.vo.Product;

/**
 * 商品持久层的代码
 * 
 * 
 */
public class ProductDao {
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

	// 首页上热门商品查询
	public List<Product> findHot() {
		// // 使用离线条件查询.
		// DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// // 查询热门的商品,条件就是is_host = 1
		// criteria.add(Restrictions.eq("is_hot", 1));
		// // 倒序排序输出:
		// criteria.addOrder(Order.desc("pdate"));
		// // 执行查询:
		// List<Object> list =
		// this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		String hql = "from Product where is_hot=? order by pdate desc";
		Query query = getSession().createQuery(hql).setInteger(0, 1);
		List<Product> list = query.setFirstResult(0).setMaxResults(9).list();
		return list;
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		// // 使用离线条件查询:
		// DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// // 按日期进行倒序排序:
		// criteria.addOrder(Order.desc("pdate"));
		// // 执行查询:
		// List<Object> list =
		// this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		String hql = "from Product order by pdate desc";
		Query query = getSession().createQuery(hql);
		List<Product> list = query.setFirstResult(0).setMaxResults(9).list();
		return list;
	}

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		// return this.getHibernateTemplate().get(Product.class, pid);
		String hql = "from Product where pid=?";
		Query query = getSession().createQuery(hql);
		Product product = (Product) query.setInteger(0, pid).uniqueResult();
		return product;
	}

	// 根据分类id查询商品的个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		Query query = getSession().createQuery(hql).setInteger(0, cid);
		List<Long> list = query.list();
		// List<Object> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据分类id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid =
		// cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid =
		// cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页另一种写法:
		// List<Product> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<Product>(hql, new Object[] { cid
		// }, begin, limit));
		Query query = getSession().createQuery(hql).setInteger(0, cid).setFirstResult(begin).setMaxResults(limit);
		List<Product> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

	// 根据二级分类查询商品个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		// List<Object> list = this.getHibernateTemplate().find(hql, csid);
		Query query = getSession().createQuery(hql).setInteger(0, csid);
		List<Long> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据二级分类查询商品信息
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		// List<Product> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<Product>(hql, new Object[] { csid
		// }, begin, limit));
		Query query = getSession().createQuery(hql).setInteger(0, csid).setFirstResult(begin).setMaxResults(limit);
		List<Product> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 后台统计商品个数的方法
	public int findCount() {
		String hql = "select count(*) from Product";
		// List<Object> list = this.getHibernateTemplate().find(hql);
		Query query = getSession().createQuery(hql);
		List<Long> list = query.list();
		if (list != null && list.size() > 0) {
			return (list.get(0)).intValue();
		}
		return 0;
	}

	// 后台查询所有商品的方法
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		// List<Product> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<Product>(hql, null, begin,
		// limit));
		Query query = getSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit);
		List<Product> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// DAO中的保存商品的方法
	public void save(Product product) {
		getSession().save(product);
	}

	// DAO中的删除商品的方法
	public void delete(Product product) {
		getSession().delete(product);
	}

	public void update(Product product) {
		getSession().update(product);
	}

}
