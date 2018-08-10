/**
 */
package com.qis.common.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 数据Entity类
 * 
 * @author
 * @version
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	protected Long createdBy; // 创建者
	protected Date createdOn; // 创建日期 查看数据库字段
	protected Long updatedBy; // 更新者
	protected Date updatedOn; // 更新日期 查看数据库字段
	protected Integer isDeleted; // 删除标记（0：正常；1：删除）



	public DataEntity() {
		super();
	}

	public DataEntity(Long id) {
		super(id);
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		this.createdOn = new Date();
		//this.updatedOn = new Date();
		this.isDeleted = 0;
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		this.updatedOn = new Date();
	}


	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedOn() {
		return createdOn;
	}

	@JsonIgnore
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@JsonIgnore
	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
