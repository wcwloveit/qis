package com.xinri.po.role;
import com.qis.common.persistence.DataEntity;

/**
 * <p></p>
 * 类名:RoleUserGroupspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class RoleUserGroups extends DataEntity<RoleUserGroups>{


    	private Long roleId;

    	private Long userGroupId;
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
	private String startUpdatedOn;
	
	/**
	 * endUpdatedOn字段查询条件结束
	 */
	private String endUpdatedOn;
	
   
	public Long getRoleId() {
		return roleId;
	}
	
    	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
    
  
	public Long getUserGroupId() {
		return userGroupId;
	}
	
    	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
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
	
	public String getStartUpdatedOn(){
		return this.startUpdatedOn;
	}

	public void setStartUpdatedOn(String startUpdatedOn){
		this.startUpdatedOn = startUpdatedOn;
	}
	
	public String getEndUpdatedOn(){
		return this.endUpdatedOn;
	}

	public void setEndUpdatedOn(String endUpdatedOn){
		this.endUpdatedOn = endUpdatedOn;
	}
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		sb.append("roleId=");
		sb.append(roleId);
		sb.append(",");   
		
		sb.append("userGroupId=");
		sb.append(userGroupId);
		sb.append("]");
   
		
		return sb.toString();
	}

}