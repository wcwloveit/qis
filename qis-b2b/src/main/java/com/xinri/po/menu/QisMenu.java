package com.xinri.po.menu;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:QisMenupo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class QisMenu extends DataEntity<QisMenu>{

    	private String icon;

    	private String name;

    	private Integer state;

    	private String url;

    	private Integer pId;
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

    
  
	public String getIcon() {
		return icon;
	}
	
    	public void setIcon(String icon) {
		this.icon = icon;
	}
    
  
	public String getName() {
		return name;
	}
	
    	public void setName(String name) {
		this.name = name;
	}
    
  
	public Integer getState() {
		return state;
	}
	
    	public void setState(Integer state) {
		this.state = state;
	}
    
  
	public String getUrl() {
		return url;
	}
	
    	public void setUrl(String url) {
		this.url = url;
	}
    
  
	public Integer getPId() {
		return pId;
	}
	
    	public void setPId(Integer pId) {
		this.pId = pId;
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

		
		
		sb.append("icon=");
		sb.append(icon);
		sb.append(",");
		
		sb.append("name=");
		sb.append(name);
		sb.append(",");
		
		sb.append("state=");
		sb.append(state);
		sb.append(",");
		
		sb.append("url=");
		sb.append(url);
		sb.append(",");
		
		sb.append("pId=");
		sb.append(pId);
		sb.append("]");
   
		
		return sb.toString();
	}

}