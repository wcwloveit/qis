package com.xinri.po.user;
import com.qis.common.persistence.DataEntity;

/**
 * <p></p>
 * 类名:UserGroupDepartmentspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class UserGroupDepartments extends DataEntity<UserGroupDepartments>{


    	private Long userGroupId;

    	private Long departmentId;
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
	
   
	public Long getUserGroupId() {
		return userGroupId;
	}
	
    	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}
    
  
	public Long getDepartmentId() {
		return departmentId;
	}
	
    	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

		sb.append("userGroupId=");
		sb.append(userGroupId);
		sb.append(",");   
		
		sb.append("departmentId=");
		sb.append(departmentId);
		sb.append("]");
   
		
		return sb.toString();
	}

}