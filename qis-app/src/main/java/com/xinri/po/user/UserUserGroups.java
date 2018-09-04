package com.xinri.po.user;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:UserUserGroupspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class UserUserGroups extends DataEntity<UserUserGroups>{


    	private Long userId;

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
	
   
	public Long getUserId() {
		return userId;
	}
	
    	public void setUserId(Long userId) {
		this.userId = userId;
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

    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		sb.append("userId=");
		sb.append(userId);
		sb.append(",");   
		
		sb.append("userGroupId=");
		sb.append(userGroupId);
		sb.append("]");
   
		
		return sb.toString();
	}

}