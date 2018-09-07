package com.xinri.util;

import javax.servlet.http.HttpServletResponse;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
public class AjaxStatus<T> {
    private Boolean success;
    private String code;
    private String message;
    private T data;
    private HttpServletResponse response;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public AjaxStatus(Boolean success) {
        this.success = success;
    }

    public AjaxStatus(Boolean success, String message) {
        this(success);
        this.message = message;
    }

    public AjaxStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxStatus(String code, String message, HttpServletResponse response) {
        this(code, message);
        this.response = response;
    }

    public AjaxStatus(Boolean success, String code, String message, T data, HttpServletResponse response) {
        this(code, message, response);
        this.data = data;
    }
}
