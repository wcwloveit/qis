package com.xinri.vo.redis;

public class Resource {
    private String url;
    private String moCode;
    private String peCode;
    private String descr;
    private String code;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMoCode() {
        return moCode;
    }

    public void setMoCode(String moCode) {
        this.moCode = moCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPeCode() {
        return peCode;

    }

    public void setPeCode(String peCode) {
        this.peCode = peCode;
    }
}
