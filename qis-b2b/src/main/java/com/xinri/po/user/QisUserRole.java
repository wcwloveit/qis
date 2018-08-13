package com.xinri.po.user;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:QisUserRolepo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class QisUserRole extends DataEntity<QisUserRole>{


    	private Long roleId;

    	private Long userId;

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
    

  
	public Long getUserId() {
		return userId;
	}
	
    	public void setUserId(Long userId) {
		this.userId = userId;
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

		
		sb.append("userId=");
		sb.append(userId);
		sb.append("]");
   
		
		return sb.toString();
	}

}