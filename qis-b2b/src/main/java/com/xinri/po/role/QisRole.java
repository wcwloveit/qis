package com.xinri.po.role;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:QisRolepo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class QisRole extends DataEntity<QisRole>{


    	private String bz;

    	private String name;

    	private String remarks;
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
    
  
	public String getBz() {
		return bz;
	}
	
    	public void setBz(String bz) {
		this.bz = bz;
	}
  
	public String getName() {
		return name;
	}
	
    	public void setName(String name) {
		this.name = name;
	}
    
  
	public String getRemarks() {
		return remarks;
	}
	
    	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

		
		
		sb.append("bz=");
		sb.append(bz);
		sb.append(",");
		
		sb.append("name=");
		sb.append(name);
		sb.append(",");
		sb.append("remarks=");
		sb.append(remarks);
		sb.append("]");
   
		
		return sb.toString();
	}

}