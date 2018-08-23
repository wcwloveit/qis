package com.xinri.po.port;

import com.qis.common.persistence.DataEntity;
import com.xinri.po.permissions.Permissions;


public class QisPort extends DataEntity<QisPort> {

    private String url;


    private String portName;


    private String portMod;

    private Integer timeout;


    private String portDesc;


    private Byte portEnv;


    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return port_name
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName
     */
    public void setPortName(String portName) {
        this.portName = portName == null ? null : portName.trim();
    }

    /**
     * @return port_mod
     */
    public String getPortMod() {
        return portMod;
    }

    /**
     * @param portMod
     */
    public void setPortMod(String portMod) {
        this.portMod = portMod == null ? null : portMod.trim();
    }

    /**
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * @return port_desc
     */
    public String getPortDesc() {
        return portDesc;
    }

    /**
     * @param portDesc
     */
    public void setPortDesc(String portDesc) {
        this.portDesc = portDesc == null ? null : portDesc.trim();
    }

    /**
     * @return port_env
     */
    public Byte getPortEnv() {
        return portEnv;
    }

    /**
     * @param portEnv
     */
    public void setPortEnv(Byte portEnv) {
        this.portEnv = portEnv;
    }
}