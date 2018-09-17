package com.xinri.po.item;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:ItemProductpo<br>
 * 创建人:wangzhen<br>
 * 创建时间:20180917<br>
 */
 
public class ItemProduct extends DataEntity<ItemProduct>{


    	private String modelCode;

    	private String colorCode;

    	private String orgCode;

    	private String modelSpecification;

    	private String modelName;

    	private BigDecimal initialPrice;

    	private String colorName;

    	private Date updatedOn;

    	private String updatedBy;

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
	
   
  
	public String getModelCode() {
		return modelCode;
	}
	
    	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
    
  
	public String getColorCode() {
		return colorCode;
	}
	
    	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
    
  
	public String getOrgCode() {
		return orgCode;
	}
	
    	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
    
  
	public String getModelSpecification() {
		return modelSpecification;
	}
	
    	public void setModelSpecification(String modelSpecification) {
		this.modelSpecification = modelSpecification;
	}
    
  
	public String getModelName() {
		return modelName;
	}
	
    	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
    
  
	public BigDecimal getInitialPrice() {
		return initialPrice;
	}
	
    	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}
    
  
	public String getColorName() {
		return colorName;
	}
	
    	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
    
  
  
  
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
    	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
    
  
	public String getUpdatedBy() {
		return updatedBy;
	}
	
    	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

		
		sb.append("modelCode=");
		sb.append(modelCode);
		sb.append(",");   
		
		sb.append("colorCode=");
		sb.append(colorCode);
		sb.append(",");   
		
		sb.append("orgCode=");
		sb.append(orgCode);
		sb.append(",");   
		
		sb.append("modelSpecification=");
		sb.append(modelSpecification);
		sb.append(",");   
		
		sb.append("modelName=");
		sb.append(modelName);
		sb.append(",");   
		
		sb.append("initialPrice=");
		sb.append(initialPrice);
		sb.append(",");   
		
		sb.append("colorName=");
		sb.append(colorName);
		sb.append(",");   
		
		
		
		sb.append("updatedOn=");
		sb.append(updatedOn);
		sb.append(",");   
		
		sb.append("updatedBy=");
		sb.append(updatedBy);
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