package com.xinri.po.role;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:QisRoleMenupo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class QisRoleMenu extends DataEntity<QisRoleMenu>{


    	private Long menuId;

    	private Long roleId;

	/**
	 * createdOn字段查询条件开始
	 */
	private String startCreatedOn;
	
	/**
	 * createdOn字段查询条件结束
	 */
	private String endCreatedOn;

	/**
	 * startUpdatedOn字段查询条件开始
	 */
	private String startModifiedOn;


	/**
	 * endUpdatedOn字段查询条件结束
	 */
	private String endModifiedOn;

	public String getStartModifiedOn() {
		return startModifiedOn;
	}

	public void setStartModifiedOn(String startModifiedOn) {
		this.startModifiedOn = startModifiedOn;
	}

	public String getEndModifiedOn() {
		return endModifiedOn;
	}

	public void setEndModifiedOn(String endModifiedOn) {
		this.endModifiedOn = endModifiedOn;
	}
	
   
  
  
	public Long getMenuId() {
		return menuId;
	}
	
    	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
    
  
	public Long getRoleId() {
		return roleId;
	}
	
    	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

  
 public String getStartCreatedOn(){
		return this.startCreatedOn;
	}

	public void setStartCreatedOn(String startCreatedOn){
		this.startCreatedOn = startCreatedOn;
	}
	
	public String getEndCreatedOn(){
		return this.endCreatedOn;
	}

	public void setEndCreatedOn(String endCreatedOn){
		this.endCreatedOn = endCreatedOn;
	}
	

    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		
		
		sb.append("menuId=");
		sb.append(menuId);
		sb.append(",");   

		
		sb.append("roleId=");
		sb.append(roleId);
		sb.append("]");
   
		
		return sb.toString();
	}

}