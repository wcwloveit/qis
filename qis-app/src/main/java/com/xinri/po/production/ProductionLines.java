package com.xinri.po.production;
import com.qis.common.persistence.DataEntity;

import java.util.*;
/**
 * <p></p>
 * 类名:ProductionLinespo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class ProductionLines extends DataEntity<ProductionLines>{


    	private String name;

    	private String code;

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

    	private Long useOrganizationId;

    	private Long useSystemId;
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
	
   
  
	public String getName() {
		return name;
	}
	
    	public void setName(String name) {
		this.name = name;
	}
    
  
	public String getCode() {
		return code;
	}
	
    	public void setCode(String code) {
		this.code = code;
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
    
  
	public Long getUseOrganizationId() {
		return useOrganizationId;
	}
	
    	public void setUseOrganizationId(Long useOrganizationId) {
		this.useOrganizationId = useOrganizationId;
	}
    
  
	public Long getUseSystemId() {
		return useSystemId;
	}
	
    	public void setUseSystemId(Long useSystemId) {
		this.useSystemId = useSystemId;
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

		
		sb.append("name=");
		sb.append(name);
		sb.append(",");   
		
		sb.append("code=");
		sb.append(code);
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
		sb.append(",");   
		
		sb.append("useOrganizationId=");
		sb.append(useOrganizationId);
		sb.append(",");   
		
		sb.append("useSystemId=");
		sb.append(useSystemId);
		sb.append("]");
   
		
		return sb.toString();
	}

}