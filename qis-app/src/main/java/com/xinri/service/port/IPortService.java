package com.xinri.service.port;
import com.qis.common.service.IBaseService;
import com.xinri.po.permissions.Permissions;
import com.xinri.po.port.QisPort;
import net.sf.json.JSONObject;


public interface IPortService extends IBaseService<QisPort>{

    public String sendPort(String portName, String getParam, JSONObject postParam);

}

