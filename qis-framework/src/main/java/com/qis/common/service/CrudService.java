/**
 */
package com.qis.common.service;

import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.DataEntity;
import com.qis.common.persistence.Page;
import com.qis.common.util.StringUtils;
//import com.qis.xsimple.ShiroUser;
//import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service基类
 * 
 * @author
 * @version
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>>
		extends BaseService implements IBaseService<T> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.getByEntity(entity);
	}

	/**
	 * 查询列表数据,带查询条件，带分页
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询列表数据，带查询条件，不带分页
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity) {
		return dao.findAllList(entity);
	}
	
	/**
	 * 查询列表数据，不带查询条件，不带分页
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList() {
		return dao.findAllList();
	}
	
	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 * @return
	 */
	public Page findPage(Page page, T entity) {
		entity.setPage(page);
		page.setData(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int saveOrUpdate(T entity) {
	//	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();// 取得当前用户信息
		if (entity.getIsNewRecord()) {
			if(entity.getCreatedBy()==null||0==entity.getCreatedBy()){
				//entity.setCreatedBy(UserUtils.getUser().getStaffId());
			//	if(user!=null){
				//	entity.setCreatedBy(user.getId());
			//	}
			}
			entity.preInsert();
			return dao.insertSelective(entity);
		} else {
			//if(user!=null) {
			//	entity.setUpdatedBy(user.getId());
			//}
			entity.preUpdate();
			return dao.update(entity);
		}
	}

	@Transactional(readOnly = false)
	public int updateByCondition(T entity) {
		//ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (StringUtils.isNotEmpty(entity.getWhereSqlParam())){
			//entity.setUpdatedBy(user.getId());
			entity.preUpdate();
			return dao.updateByCondition(entity);
		}
			
		return -1;
	}
	@Transactional(readOnly = false)
	public int removeByCondition(T entity) {
		return dao.removeByCondition(entity);
	}

	public List<T> findByCondition(T entity) {
		return dao.findByCondition(entity);
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int delete(T entity) {
		return dao.deleteByEntity(entity);
	}

	@Transactional(readOnly = false)
	public int deleteById(Long id) {
		return dao.delete(id);
	}

	@Transactional(readOnly = false)
	public void batchDelete(List<Long> ids) {
		dao.batchDelete(ids);
	}

	@Transactional(readOnly = false)
	public void batchRemove(List<Long> ids) {
		dao.batchRemove(ids);
	}

	@Transactional(readOnly = false)
	public int remove(Long id) {
		return dao.remove(id);
	}

	/**
	 * 删除数据
	 * add by zhoubao on 20160505
	 * mapper没有按此方法生成，调用时，需要自己编写此mapper
	 * 参考dict/DepartmentLeaderMapper.xml
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int removeByEntity(T entity) {
		return dao.removeByEntity(entity);
	}
	
	/**
	 * 删除数据
	 * add by zhoubao on 20160505
	 * mapper原先已生成此方法
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteByEntity(T entity) {
		return dao.deleteByEntity(entity);
	}	
}
