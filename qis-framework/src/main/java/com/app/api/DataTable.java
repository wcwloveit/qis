package com.app.api;
import java.io.Serializable;
import java.util.List;
public class DataTable<T> implements Serializable{
    private static final long serialVersionUID=2252240868205663450L;
    //-- 分页参数 --//
    protected int sEcho=1;
    protected int iDisplayStart=0;
    protected int iDisplayLength=20;
    protected long iTotalRecords;
    protected long iTotalDisplayRecords;
    protected List<T> aaData;//返回结果
    private String sortValue;
    private String sortColName;
    private String sSearch;
    private String iSortCol_0;
    private String sSortDir_0;
    private String[] iSortCols;
    private String[] sSortDirs;
    private long iSortingCols;
    public int getsEcho(){
        return sEcho;
    }
    public void setsEcho(int sEcho){
        this.sEcho=sEcho;
    }
    public int getiDisplayStart(){
        return iDisplayStart;
    }
    public void setiDisplayStart(int iDisplayStart){
        this.iDisplayStart=iDisplayStart;
    }
    public int getiDisplayLength(){
        if(iDisplayLength==-1){
            iDisplayLength=Integer.MAX_VALUE;
        }
        return iDisplayLength;
    }
    public void setiDisplayLength(int iDisplayLength){
        this.iDisplayLength=iDisplayLength;
    }
    public long getiTotalRecords(){
        return iTotalRecords;
    }
    public void setiTotalRecords(long iTotalRecords){
        this.iTotalRecords=iTotalRecords;
    }
    public long getiTotalDisplayRecords(){
        return iTotalDisplayRecords;
    }
    public void setiTotalDisplayRecords(long iTotalDisplayRecords){
        this.iTotalDisplayRecords=iTotalDisplayRecords;
    }
    public List<T> getAaData(){
        return aaData;
    }
    public void setAaData(List<T> aaData){
        this.aaData=aaData;
    }
    public String getsSearch(){
        return sSearch;
    }
    public void setsSearch(String sSearch){
        if(sSearch!=null){
            this.sSearch=sSearch.trim();
        }else{
            this.sSearch=null;
        }
    }
    public String getSortColName(){
        return sortColName;
    }
    public void setSortColName(String sortColName){
        this.sortColName=sortColName;
    }
    public String getSortValue(){
        return sortValue;
    }
    public void setSortValue(String sortValue){
        this.sortValue=sortValue;
    }
    public String getiSortCol_0(){
        return iSortCol_0;
    }
    public void setiSortCol_0(String iSortCol_0){
        this.iSortCol_0=iSortCol_0;
    }
    public String getsSortDir_0(){
        return sSortDir_0;
    }
    public void setsSortDir_0(String sSortDir_0){
        this.sSortDir_0=sSortDir_0;
    }

    public String[] getiSortCols() {
        return iSortCols;
    }

    public void setiSortCols(String[] iSortCols) {
        this.iSortCols = iSortCols;
    }

    public String[] getsSortDirs() {
        return sSortDirs;
    }

    public void setsSortDirs(String[] sSortDirs) {
        this.sSortDirs = sSortDirs;
    }

    public long getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(long iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public int pageNo(){
        if(this.getiDisplayLength()!=0){
            return this.iDisplayStart/this.getiDisplayLength();
        }
        return -1;
    }
}
