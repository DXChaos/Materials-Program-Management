package com.bjsxt.drp.business.itemmgr.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsxt.drp.business.itemmgr.model.Item;
import com.bjsxt.drp.business.util.AppException;
import com.bjsxt.drp.business.util.PageModel;

/**
 * 物料管理类
 * @author Administrator
 *
 */
public class ItemManagerImpl extends HibernateDaoSupport implements ItemManager {

	/**
	 * 添加物料
	 * @param item item对象
	 */
	public void addItem(Item item) {
		try {
			this.getHibernateTemplate().save(item);
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			throw new AppException("drp.basedata.item.error.add"); 
		}
	}

	/**
	 * 修改物料
	 * @param item item对象
	 */
	public void modifyItem(Item item) {
		try {
			this.getHibernateTemplate().update(item);
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			throw new AppException("drp.database.item.error.modify", item.getItemNo());
		}
	}

	/**
	 * 删除物料
	 * @param itemNoList 物料代码集合
	 */
	public void deleteItem(String[] itemNoList) {
		try {
			for (int i=0; i<itemNoList.length; i++) {
				Item item = (Item)this.getHibernateTemplate().load(Item.class, itemNoList[i]);
				this.getHibernateTemplate().delete(item);
			}
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			throw new AppException("drp.basedata.item.error.delete");
		}		
	}

	/**
	 * 根据条件查询物料信息
	 * @param queryStr 查询条件
	 * @return item对象的集合
	 */
	public PageModel findAllItem(final int pageNo, final int pageSize, final String queryStr) {
		PageModel pageModel = null;
		List itemList = new ArrayList();
		try {
			if (queryStr != null && queryStr.trim().length() != 0) {
				itemList = this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery("from Item a where a.itemNo like ? or a.itemName like ? order by a.itemNo")
										.setParameter(0, queryStr + "%")
										.setParameter(1, queryStr + "%")
										.setFirstResult((pageNo - 1) * pageSize)
										.setMaxResults(pageSize)
										.list();
					}
				});
			}else {
				itemList = this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery("select a from Item a join fetch a.category b join fetch a.unit c order by a.itemNo")
						.setFirstResult((pageNo - 1) * pageSize)
						.setMaxResults(pageSize)
						.list();
					}
				});
			}
			
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(itemList);
			pageModel.setTotalRecords(getTotalRecords(queryStr));
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			throw new AppException("drp.database.item.error.findallitem");
		}	
		return pageModel;

	}
	
	/**
	 * 查询记录数
	 * 
	 * @param session
	 * @param queryStr
	 * @return
	 */
	private int getTotalRecords(String queryStr) {
		Long totalRecords = 0L;
		if (queryStr != null && queryStr.trim().length() != 0) {
			//采用模板取得总记录数据
			totalRecords = (Long)this.getHibernateTemplate().find("select count(*) from Item a where a.itemNo like ? or a.itemName like ?", 
											 new Object[]{queryStr + "%", queryStr + "%"}).get(0);
		}else {
			//采用session取得总记录数
			totalRecords = (Long)this.getSession().createQuery("select count(*) from Item a").uniqueResult();
		}
		return totalRecords.intValue();
	}
	
	/**
	 * 根据Id查询物料
	 * @param item item对象
	 */
	public Item findItemById(String itemNo) {
		Item item = null;
		try {
			item = (Item)this.getHibernateTemplate().load(Item.class, itemNo);
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			throw new AppException("drp.basedata.item.error.delete");
		}	
		return item;
	}
}