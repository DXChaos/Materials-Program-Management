package com.bjsxt.drp.business.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * DRP函数库
 * 注意开发函数库方法必须是静态的
 * @author Administrator
 *
 */
public class Functions {
	
	private static SessionFactory sessionFactory;
	
	/**
	 * 返回物料类别的集合
	 * @return ItemCategory对象的集合
	 */
	public static List getItemCategoryList() {
		List itemCategoryList = null; 
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from ItemCategory a order by a.id");
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return itemCategoryList;
	}

	/**
	 * 返回物料类别的集合
	 * @return ItemCategory对象的集合
	 */
	public static List getItemUnitList() {
		List itemUnitList = null; 
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from ItemUnit a order by a.id");
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return itemUnitList;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		Functions.sessionFactory = sessionFactory;
	}
}
