package com.xinri.po.user;

import com.qis.common.persistence.DataEntity;

import java.util.Date;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
 
public class SysUser extends DataEntity<SysUser>{


    	private String avatar;

    	private String account;

    	private String password;

    	private String salt;

    	private String name;

    	private Date birthday;

    	private Integer sex;

    	private String email;

    	private String phone;

    	private String roleid;

    	private Long deptid;

    	private Integer status;

    	private Date createtime;

    	private Integer version;
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
  
	public String getAvatar() {
		return avatar;
	}
	
    	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
  
	public String getAccount() {
		return account;
	}
	
    	public void setAccount(String account) {
		this.account = account;
	}
    
  
	public String getPassword() {
		return password;
	}
	
    	public void setPassword(String password) {
		this.password = password;
	}
    
  
	public String getSalt() {
		return salt;
	}
	
    	public void setSalt(String salt) {
		this.salt = salt;
	}
    
  
	public String getName() {
		return name;
	}
	
    	public void setName(String name) {
		this.name = name;
	}
    

	public Date getBirthday() {
		return birthday;
	}
	
    	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
    
  
	public Integer getSex() {
		return sex;
	}
	
    	public void setSex(Integer sex) {
		this.sex = sex;
	}

  
	public String getEmail() {
		return email;
	}
	
    	public void setEmail(String email) {
		this.email = email;
	}
    
  
	public String getPhone() {
		return phone;
	}
	
    	public void setPhone(String phone) {
		this.phone = phone;
	}
    
  
	public String getRoleid() {
		return roleid;
	}
	
    	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
    
  
	public Long getDeptid() {
		return deptid;
	}
	
    	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}
    
  
	public Integer getStatus() {
		return status;
	}
	
    	public void setStatus(Integer status) {
		this.status = status;
	}
    
  
	public Date getCreatetime() {
		return createtime;
	}
	
    	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    
  
	public Integer getVersion() {
		return version;
	}
	
    	public void setVersion(Integer version) {
		this.version = version;
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

		
		
		sb.append("avatar=");
		sb.append(avatar);
		sb.append(",");   

		
		sb.append("account=");
		sb.append(account);
		sb.append(",");   
		
		sb.append("password=");
		sb.append(password);
		sb.append(",");   

		
		sb.append("salt=");
		sb.append(salt);
		sb.append(",");   
		
		sb.append("name=");
		sb.append(name);
		sb.append(",");   

		
		sb.append("birthday=");
		sb.append(birthday);
		sb.append(",");   
		
		sb.append("sex=");
		sb.append(sex);
		sb.append(",");   

		
		sb.append("email=");
		sb.append(email);
		sb.append(",");   
		
		sb.append("phone=");
		sb.append(phone);
		sb.append(",");   

		
		sb.append("roleid=");
		sb.append(roleid);
		sb.append(",");   
		
		sb.append("deptid=");
		sb.append(deptid);
		sb.append(",");   

		
		sb.append("status=");
		sb.append(status);
		sb.append(",");   
		
		sb.append("createtime=");
		sb.append(createtime);
		sb.append(",");   

		
		sb.append("version=");
		sb.append(version);
		sb.append("]");
   
		
		return sb.toString();
	}

}