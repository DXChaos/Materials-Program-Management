package com.bjsxt.drp.business.itemmgr.manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsxt.drp.business.itemmgr.model.Item;
import com.bjsxt.drp.business.util.AppException;
import com.bjsxt.drp.business.util.HibernateUtils;
import com.bjsxt.drp.business.util.PageModel;

/**
 * 物料管理类 
 * @author Administrator
 *
 */
public interface ItemManager {

	/**
	 * 添加物料
	 * @param item item对象
	 */
	public void addItem(Item item);

	/**
	 * 修改物料
	 * @param item item对象
	 */
	public void modifyItem(Item item);

	/**
	 * 删除物料
	 * @param itemNoList 物料代码集合
	 */
	public void deleteItem(String[] itemNoList);

	/**
	 * 根据条件查询物料信息
	 * @param queryStr 查询条件
	 * @return item对象的集合
	 */
	public PageModel findAllItem(int pageNo, int pageSize, String queryStr);
	
	/**
	 * 根据Id查询物料
	 * @param item item对象
	 */
	public Item findItemById(String itemNo);
}