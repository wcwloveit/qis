package com.xinri.po.remote;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:SetsRemoteEndPointpo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class SetsRemoteEndPoint extends DataEntity<SetsRemoteEndPoint>{


    	private String setsIp;

    	private String setsPoint;

    	private String remoteEndIp;

    	private String remoteEndPoint;
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
	
   
	public String getSetsIp() {
		return setsIp;
	}
	
    	public void setSetsIp(String setsIp) {
		this.setsIp = setsIp;
	}
    
  
	public String getSetsPoint() {
		return setsPoint;
	}
	
    	public void setSetsPoint(String setsPoint) {
		this.setsPoint = setsPoint;
	}
    
  
	public String getRemoteEndIp() {
		return remoteEndIp;
	}
	
    	public void setRemoteEndIp(String remoteEndIp) {
		this.remoteEndIp = remoteEndIp;
	}
    
  
	public String getRemoteEndPoint() {
		return remoteEndPoint;
	}
	
    	public void setRemoteEndPoint(String remoteEndPoint) {
		this.remoteEndPoint = remoteEndPoint;
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

		sb.append("setsIp=");
		sb.append(setsIp);
		sb.append(",");   
		
		sb.append("setsPoint=");
		sb.append(setsPoint);
		sb.append(",");   
		
		sb.append("remoteEndIp=");
		sb.append(remoteEndIp);
		sb.append(",");   
		
		sb.append("remoteEndPoint=");
		sb.append(remoteEndPoint);
		sb.append("]");
   
		
		return sb.toString();
	}

}