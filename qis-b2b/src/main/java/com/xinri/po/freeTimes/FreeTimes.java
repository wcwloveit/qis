package com.xinri.po.freeTimes;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:FreeTimespo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class FreeTimes extends DataEntity<FreeTimes>{


    	private Date stopStartTime;

    	private Date stopEndTime;

    	private String reason;

    	private BigDecimal stopTimeLength;

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

    	private Long screenBoardDataId;

    	private Long stopTypeId;

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
	private String startUpdatedOn;
	
	/**
	 * endUpdatedOn字段查询条件结束
	 */
	private String endUpdatedOn;
	
   
  
	public Date getStopStartTime() {
		return stopStartTime;
	}
	
    	public void setStopStartTime(Date stopStartTime) {
		this.stopStartTime = stopStartTime;
	}
    
  
	public Date getStopEndTime() {
		return stopEndTime;
	}
	
    	public void setStopEndTime(Date stopEndTime) {
		this.stopEndTime = stopEndTime;
	}
    
  
	public String getReason() {
		return reason;
	}
	
    	public void setReason(String reason) {
		this.reason = reason;
	}
    
  
	public BigDecimal getStopTimeLength() {
		return stopTimeLength;
	}
	
    	public void setStopTimeLength(BigDecimal stopTimeLength) {
		this.stopTimeLength = stopTimeLength;
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
    
  
	public Long getScreenBoardDataId() {
		return screenBoardDataId;
	}
	
    	public void setScreenBoardDataId(Long screenBoardDataId) {
		this.screenBoardDataId = screenBoardDataId;
	}
    
  
	public Long getStopTypeId() {
		return stopTypeId;
	}
	
    	public void setStopTypeId(Long stopTypeId) {
		this.stopTypeId = stopTypeId;
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

		
		sb.append("stopStartTime=");
		sb.append(stopStartTime);
		sb.append(",");   
		
		sb.append("stopEndTime=");
		sb.append(stopEndTime);
		sb.append(",");   
		
		sb.append("reason=");
		sb.append(reason);
		sb.append(",");   
		
		sb.append("stopTimeLength=");
		sb.append(stopTimeLength);
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
		
		sb.append("screenBoardDataId=");
		sb.append(screenBoardDataId);
		sb.append(",");   
		
		sb.append("stopTypeId=");
		sb.append(stopTypeId);
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