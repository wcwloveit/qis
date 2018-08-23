package com.app.api;
import java.io.Serializable;

/**
 * @author chirs@zhoujin.com (Chirs Chou)
 */
public class JsTree implements Serializable {
    private static final long serialVersionUID=-283431484704582527L;
    private String id;
    private String parent;
    private String text;
    private String icon;
    private String types;//用于人员选择界面，o 部门 p 人员
	private JsTreeState state = new JsTreeState();
    private Object children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent(){
        return parent;
    }
    public void setParent(String parent){
        this.parent=parent;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }
    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon=icon;
    }
    public JsTreeState getState(){
        return state;
    }
    public void setState(JsTreeState state){
        this.state=state;
    }
    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
    public String getTypes(){
        return types;
    }
    public void setTypes(String types){
        this.types=types;
    }
	/**
	 * @return the children
	 */
	public Object getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(Object children) {
		this.children = children;
	}
}
