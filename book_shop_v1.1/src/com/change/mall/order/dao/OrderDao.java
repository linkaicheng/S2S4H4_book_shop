package com.change.mall.order.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.change.mall.order.vo.Order;
import com.change.mall.order.vo.OrderItem;

public class OrderDao {
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

	// Dao层的保存订单额操作
	public void save(Order order) {
		getSession().save(order);
	}

	// Dao层查询我的订单分页查询:统计个数
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		// List<Object> list = this.getHibernateTemplate().find(hql, uid);
		Query query = getSession().createQuery(hql).setInteger(0, uid);
		List<Long> list = query.list();
		if (list != null && list.size() > 0) {
			return (list.get(0)).intValue();
		}
		return 0;
	}

	// Dao层查询我的订单分页查询:查询数据
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		// List<Order> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<Order>(hql, new Object[] { uid },
		// begin, limit));
		Query query = getSession().createQuery(hql).setInteger(0, uid).setFirstResult(begin).setMaxResults(limit);
		List<Order> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// DAO层根据订单id查询订单
	public Order findByOid(Integer oid) {
		String hql = "from Order where oid=?";
		Query query = getSession().createQuery(hql).setInteger(0, oid);
		Order order = (Order) query.uniqueResult();
		return order;
	}

	// DAO层修改订单的方法:
	public void update(Order currOrder) {
		getSession().update(currOrder);
	}

	// DAO中统计订单个数的方法
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = getSession().createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return (list.get(0)).intValue();
		}
		return 0;
	}

	// DAO中分页查询订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		// List<Order> list = this.getHibernateTemplate()
		// .execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		Query query = getSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit);
		List<Order> list = query.list();
		return list;
	}

	// DAo中根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		Query query = getSession().createQuery(hql).setInteger(0, oid);
		List<OrderItem> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
