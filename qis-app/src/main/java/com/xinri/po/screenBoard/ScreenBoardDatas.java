package com.xinri.po.screenBoard;
import com.qis.common.persistence.DataEntity;
import java.math.*;
import java.util.*;
/**
 * <p></p>
 * 类名:ScreenBoardDataspo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class ScreenBoardDatas extends DataEntity<ScreenBoardDatas>{


    	private Integer planCount;

    	private Date startTime;

    	private Date endTime;

    	private BigDecimal planTime;

    	private String productName;

    	private String productModeName;

    	private Integer planEfficiency;

    	private Integer speedEfficiency;

    	private Integer actualEfficiency;

    	private Integer planOfflineCount;

    	private Integer actualOfflineCount;

    	private BigDecimal freeTimeTotalCount;

    	private BigDecimal freeTimeInterval;

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

    	private Long freeTimeIntervalUnitId;

    	private Long freeTimeTotalCountUnitId;

    	private Long planTimeUnitId;

    	private Long productionLineId;

    	private Long runStatusId;

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
	
   
  
	public Integer getPlanCount() {
		return planCount;
	}
	
    	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}
    
  
	public Date getStartTime() {
		return startTime;
	}
	
    	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
  
	public Date getEndTime() {
		return endTime;
	}
	
    	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
  
	public BigDecimal getPlanTime() {
		return planTime;
	}
	
    	public void setPlanTime(BigDecimal planTime) {
		this.planTime = planTime;
	}
    
  
	public String getProductName() {
		return productName;
	}
	
    	public void setProductName(String productName) {
		this.productName = productName;
	}
    
  
	public String getProductModeName() {
		return productModeName;
	}
	
    	public void setProductModeName(String productModeName) {
		this.productModeName = productModeName;
	}
    
  
	public Integer getPlanEfficiency() {
		return planEfficiency;
	}
	
    	public void setPlanEfficiency(Integer planEfficiency) {
		this.planEfficiency = planEfficiency;
	}
    
  
	public Integer getSpeedEfficiency() {
		return speedEfficiency;
	}
	
    	public void setSpeedEfficiency(Integer speedEfficiency) {
		this.speedEfficiency = speedEfficiency;
	}
    
  
	public Integer getActualEfficiency() {
		return actualEfficiency;
	}
	
    	public void setActualEfficiency(Integer actualEfficiency) {
		this.actualEfficiency = actualEfficiency;
	}
    
  
	public Integer getPlanOfflineCount() {
		return planOfflineCount;
	}
	
    	public void setPlanOfflineCount(Integer planOfflineCount) {
		this.planOfflineCount = planOfflineCount;
	}
    
  
	public Integer getActualOfflineCount() {
		return actualOfflineCount;
	}
	
    	public void setActualOfflineCount(Integer actualOfflineCount) {
		this.actualOfflineCount = actualOfflineCount;
	}
    
  
	public BigDecimal getFreeTimeTotalCount() {
		return freeTimeTotalCount;
	}
	
    	public void setFreeTimeTotalCount(BigDecimal freeTimeTotalCount) {
		this.freeTimeTotalCount = freeTimeTotalCount;
	}
    
  
	public BigDecimal getFreeTimeInterval() {
		return freeTimeInterval;
	}
	
    	public void setFreeTimeInterval(BigDecimal freeTimeInterval) {
		this.freeTimeInterval = freeTimeInterval;
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
    
  
	public Long getFreeTimeIntervalUnitId() {
		return freeTimeIntervalUnitId;
	}
	
    	public void setFreeTimeIntervalUnitId(Long freeTimeIntervalUnitId) {
		this.freeTimeIntervalUnitId = freeTimeIntervalUnitId;
	}
    
  
	public Long getFreeTimeTotalCountUnitId() {
		return freeTimeTotalCountUnitId;
	}
	
    	public void setFreeTimeTotalCountUnitId(Long freeTimeTotalCountUnitId) {
		this.freeTimeTotalCountUnitId = freeTimeTotalCountUnitId;
	}
    
  
	public Long getPlanTimeUnitId() {
		return planTimeUnitId;
	}
	
    	public void setPlanTimeUnitId(Long planTimeUnitId) {
		this.planTimeUnitId = planTimeUnitId;
	}
    
  
	public Long getProductionLineId() {
		return productionLineId;
	}
	
    	public void setProductionLineId(Long productionLineId) {
		this.productionLineId = productionLineId;
	}
    
  
	public Long getRunStatusId() {
		return runStatusId;
	}
	
    	public void setRunStatusId(Long runStatusId) {
		this.runStatusId = runStatusId;
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

		
		sb.append("planCount=");
		sb.append(planCount);
		sb.append(",");   
		
		sb.append("startTime=");
		sb.append(startTime);
		sb.append(",");   
		
		sb.append("endTime=");
		sb.append(endTime);
		sb.append(",");   
		
		sb.append("planTime=");
		sb.append(planTime);
		sb.append(",");   
		
		sb.append("productName=");
		sb.append(productName);
		sb.append(",");   
		
		sb.append("productModeName=");
		sb.append(productModeName);
		sb.append(",");   
		
		sb.append("planEfficiency=");
		sb.append(planEfficiency);
		sb.append(",");   
		
		sb.append("speedEfficiency=");
		sb.append(speedEfficiency);
		sb.append(",");   
		
		sb.append("actualEfficiency=");
		sb.append(actualEfficiency);
		sb.append(",");   
		
		sb.append("planOfflineCount=");
		sb.append(planOfflineCount);
		sb.append(",");   
		
		sb.append("actualOfflineCount=");
		sb.append(actualOfflineCount);
		sb.append(",");   
		
		sb.append("freeTimeTotalCount=");
		sb.append(freeTimeTotalCount);
		sb.append(",");   
		
		sb.append("freeTimeInterval=");
		sb.append(freeTimeInterval);
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
		
		sb.append("freeTimeIntervalUnitId=");
		sb.append(freeTimeIntervalUnitId);
		sb.append(",");   
		
		sb.append("freeTimeTotalCountUnitId=");
		sb.append(freeTimeTotalCountUnitId);
		sb.append(",");   
		
		sb.append("planTimeUnitId=");
		sb.append(planTimeUnitId);
		sb.append(",");   
		
		sb.append("productionLineId=");
		sb.append(productionLineId);
		sb.append(",");   
		
		sb.append("runStatusId=");
		sb.append(runStatusId);
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