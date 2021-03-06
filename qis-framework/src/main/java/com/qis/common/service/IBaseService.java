package com.qis.common.service;

import com.qis.common.persistence.Page;

import java.util.List;

public interface IBaseService<T>{
	public T get(Long id) ;
	public T get(T entity);
	public List<T> findList(T entity);
	public List<T> findAllList(T entity);
	public List<T> findAllList();
	public Page findPage(Page page, T entity);
	public int saveOrUpdate(T entity) ;
	public int updateByCondition(T entity);
	public int removeByCondition(T entity);
	public List<T> findByCondition(T entity);
	public int delete(T entity);
	public int deleteById(Long id) ;
	public void batchDelete(List<Long> ids) ;
	public void batchRemove(List<Long> ids) ;
	public int remove(Long id);
	public int removeByEntity(T entity);
	public int deleteByEntity(T entity);
}
