package com.xinri.po.user;
import com.qis.common.persistence.DataEntity;

import java.util.*;
/**
 * <p></p>
 * 类名:Userspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class Users extends DataEntity<Users>{

	    private String salt;

    	private String user;

    	private Long currentConnections;

    	private Long departmentId;

    	private Long totalConnections;

    	private Long organizationId;

    	private String userName;

    	private String password;

    	private String mobilePhone;

    	private String userNo;

    	private String name;

    	private Integer userUseType;

    	private Integer permissionOverFlag;

    	private String descr;

    	private String guidId;

    	private Integer isEffective;

    	private Date effectiveDateStart;

    	private Date effectiveDateEnd;

    	private String descFlexField1;

    	private String descFlexField2;

    	private String descFlexField3;

    	private String descFlexField4;

    	private String descFlexField5;

    	private String descFlexField6;
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


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

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
	
   
	public String getUser() {
		return user;
	}
	
    	public void setUser(String user) {
		this.user = user;
	}
    
  
  
	public Long getCurrentConnections() {
		return currentConnections;
	}
	
    	public void setCurrentConnections(Long currentConnections) {
		this.currentConnections = currentConnections;
	}
    
  
	public Long getDepartmentId() {
		return departmentId;
	}
	
    	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
    
  
	public Long getTotalConnections() {
		return totalConnections;
	}
	
    	public void setTotalConnections(Long totalConnections) {
		this.totalConnections = totalConnections;
	}
    
  
	public Long getOrganizationId() {
		return organizationId;
	}
	
    	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
    
  
	public String getUserName() {
		return userName;
	}
	
    	public void setUserName(String userName) {
		this.userName = userName;
	}
    
  
	public String getPassword() {
		return password;
	}
	
    	public void setPassword(String password) {
		this.password = password;
	}
    
  
	public String getMobilePhone() {
		return mobilePhone;
	}
	
    	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
    
  
	public String getUserNo() {
		return userNo;
	}
	
    	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
    
  
	public String getName() {
		return name;
	}
	
    	public void setName(String name) {
		this.name = name;
	}
    
  
	public Integer getUserUseType() {
		return userUseType;
	}
	
    	public void setUserUseType(Integer userUseType) {
		this.userUseType = userUseType;
	}
    
  
	public Integer getPermissionOverFlag() {
		return permissionOverFlag;
	}
	
    	public void setPermissionOverFlag(Integer permissionOverFlag) {
		this.permissionOverFlag = permissionOverFlag;
	}
    
  
  
	public String getDescr() {
		return descr;
	}
	
    	public void setDescr(String descr) {
		this.descr = descr;
	}
    
  
	public String getGuidId() {
		return guidId;
	}
	
    	public void setGuidId(String guidId) {
		this.guidId = guidId;
	}
    
  
	public Integer getIsEffective() {
		return isEffective;
	}
	
    	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
    
  
	public Date getEffectiveDateStart() {
		return effectiveDateStart;
	}
	
    	public void setEffectiveDateStart(Date effectiveDateStart) {
		this.effectiveDateStart = effectiveDateStart;
	}
    
  
	public Date getEffectiveDateEnd() {
		return effectiveDateEnd;
	}
	
    	public void setEffectiveDateEnd(Date effectiveDateEnd) {
		this.effectiveDateEnd = effectiveDateEnd;
	}
    
  
  
  
  
  
	public String getDescFlexField1() {
		return descFlexField1;
	}
	
    	public void setDescFlexField1(String descFlexField1) {
		this.descFlexField1 = descFlexField1;
	}
    
  
	public String getDescFlexField2() {
		return descFlexField2;
	}
	
    	public void setDescFlexField2(String descFlexField2) {
		this.descFlexField2 = descFlexField2;
	}
    
  
	public String getDescFlexField3() {
		return descFlexField3;
	}
	
    	public void setDescFlexField3(String descFlexField3) {
		this.descFlexField3 = descFlexField3;
	}
    
  
	public String getDescFlexField4() {
		return descFlexField4;
	}
	
    	public void setDescFlexField4(String descFlexField4) {
		this.descFlexField4 = descFlexField4;
	}
    
  
	public String getDescFlexField5() {
		return descFlexField5;
	}
	
    	public void setDescFlexField5(String descFlexField5) {
		this.descFlexField5 = descFlexField5;
	}
    
  
	public String getDescFlexField6() {
		return descFlexField6;
	}
	
    	public void setDescFlexField6(String descFlexField6) {
		this.descFlexField6 = descFlexField6;
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

		sb.append("user=");
		sb.append(user);
		sb.append(",");   
		
		
		sb.append("currentConnections=");
		sb.append(currentConnections);
		sb.append(",");   
		
		sb.append("departmentId=");
		sb.append(departmentId);
		sb.append(",");   
		
		sb.append("totalConnections=");
		sb.append(totalConnections);
		sb.append(",");   
		
		sb.append("organizationId=");
		sb.append(organizationId);
		sb.append(",");   
		
		sb.append("userName=");
		sb.append(userName);
		sb.append(",");   
		
		sb.append("password=");
		sb.append(password);
		sb.append(",");   
		
		sb.append("mobilePhone=");
		sb.append(mobilePhone);
		sb.append(",");   
		
		sb.append("userNo=");
		sb.append(userNo);
		sb.append(",");   
		
		sb.append("name=");
		sb.append(name);
		sb.append(",");   
		
		sb.append("userUseType=");
		sb.append(userUseType);
		sb.append(",");   
		
		sb.append("permissionOverFlag=");
		sb.append(permissionOverFlag);
		sb.append(",");   
		
		
		sb.append("descr=");
		sb.append(descr);
		sb.append(",");   
		
		sb.append("guidId=");
		sb.append(guidId);
		sb.append(",");   
		
		sb.append("isEffective=");
		sb.append(isEffective);
		sb.append(",");   
		
		sb.append("effectiveDateStart=");
		sb.append(effectiveDateStart);
		sb.append(",");   
		
		sb.append("effectiveDateEnd=");
		sb.append(effectiveDateEnd);
		sb.append(",");   
		
		
		
		
		
		sb.append("descFlexField1=");
		sb.append(descFlexField1);
		sb.append(",");   
		
		sb.append("descFlexField2=");
		sb.append(descFlexField2);
		sb.append(",");   
		
		sb.append("descFlexField3=");
		sb.append(descFlexField3);
		sb.append(",");   
		
		sb.append("descFlexField4=");
		sb.append(descFlexField4);
		sb.append(",");   
		
		sb.append("descFlexField5=");
		sb.append(descFlexField5);
		sb.append(",");   
		
		sb.append("descFlexField6=");
		sb.append(descFlexField6);
		sb.append("]");
   
		
		return sb.toString();
	}

}