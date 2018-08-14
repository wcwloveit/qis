package com.xinri.po.dictionary;
import com.qis.common.persistence.DataEntity;

/**
 * <p></p>
 * 类名:Dictionarypo<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 
public class Dictionary extends DataEntity<Dictionary>{


    	private String dicKey;

    	private String dicValue;

    	private Long dicPid;

    	private String type;

    	private Long levelId;

    	private Integer sort;

    	private String path;

    	private String dictionaryType;
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
	
   
  
	public String getDicKey() {
		return dicKey;
	}
	
    	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
    
  
	public String getDicValue() {
		return dicValue;
	}
	
    	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
    
  
	public Long getDicPid() {
		return dicPid;
	}
	
    	public void setDicPid(Long dicPid) {
		this.dicPid = dicPid;
	}
    
  
	public String getType() {
		return type;
	}
	
    	public void setType(String type) {
		this.type = type;
	}
    
  
	public Long getLevelId() {
		return levelId;
	}
	
    	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
    
  
	public Integer getSort() {
		return sort;
	}
	
    	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
  
	public String getPath() {
		return path;
	}
	
    	public void setPath(String path) {
		this.path = path;
	}
    
  
	public String getDictionaryType() {
		return dictionaryType;
	}
	
    	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
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

		
		sb.append("dicKey=");
		sb.append(dicKey);
		sb.append(",");   
		
		sb.append("dicValue=");
		sb.append(dicValue);
		sb.append(",");   
		
		sb.append("dicPid=");
		sb.append(dicPid);
		sb.append(",");   
		
		sb.append("type=");
		sb.append(type);
		sb.append(",");   
		
		sb.append("levelId=");
		sb.append(levelId);
		sb.append(",");   
		
		sb.append("sort=");
		sb.append(sort);
		sb.append(",");   
		
		sb.append("path=");
		sb.append(path);
		sb.append(",");   
		
		sb.append("dictionaryType=");
		sb.append(dictionaryType);
		sb.append("]");
   
		
		return sb.toString();
	}

}