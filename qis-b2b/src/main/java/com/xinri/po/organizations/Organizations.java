package com.xinri.po.organizations;
import com.qis.common.persistence.DataEntity;

import java.util.*;
/**
 * <p></p>
 * 类名:Organizationspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class Organizations extends DataEntity<Organizations>{


    	private String name;

    	private String code;

    	private Long parentOrganizationId;

    	private Integer depthLevel;

    	private String u9No;

    	private String u9Id;

    	private String oaNo;

    	private String oaId;

    	private String oaCanceled;

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
	private String startUpdatedOn;
	
	/**
	 * endUpdatedOn字段查询条件结束
	 */
	private String endUpdatedOn;
	
   
  
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
    
  
	public Long getParentOrganizationId() {
		return parentOrganizationId;
	}
	
    	public void setParentOrganizationId(Long parentOrganizationId) {
		this.parentOrganizationId = parentOrganizationId;
	}
    
  
	public Integer getDepthLevel() {
		return depthLevel;
	}
	
    	public void setDepthLevel(Integer depthLevel) {
		this.depthLevel = depthLevel;
	}
    
  
	public String getU9No() {
		return u9No;
	}
	
    	public void setU9No(String u9No) {
		this.u9No = u9No;
	}
    
  
	public String getU9Id() {
		return u9Id;
	}
	
    	public void setU9Id(String u9Id) {
		this.u9Id = u9Id;
	}
    
  
	public String getOaNo() {
		return oaNo;
	}
	
    	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
    
  
	public String getOaId() {
		return oaId;
	}
	
    	public void setOaId(String oaId) {
		this.oaId = oaId;
	}
    
  
	public String getOaCanceled() {
		return oaCanceled;
	}
	
    	public void setOaCanceled(String oaCanceled) {
		this.oaCanceled = oaCanceled;
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

		
		sb.append("name=");
		sb.append(name);
		sb.append(",");   
		
		sb.append("code=");
		sb.append(code);
		sb.append(",");   
		
		sb.append("parentOrganizationId=");
		sb.append(parentOrganizationId);
		sb.append(",");   
		
		sb.append("depthLevel=");
		sb.append(depthLevel);
		sb.append(",");   
		
		sb.append("u9No=");
		sb.append(u9No);
		sb.append(",");   
		
		sb.append("u9Id=");
		sb.append(u9Id);
		sb.append(",");   
		
		sb.append("oaNo=");
		sb.append(oaNo);
		sb.append(",");   
		
		sb.append("oaId=");
		sb.append(oaId);
		sb.append(",");   
		
		sb.append("oaCanceled=");
		sb.append(oaCanceled);
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