package com.xinri.service.port.impl;

import com.qis.common.service.CrudService;
import com.qis.common.util.HttpRequestUtils;
import com.xinri.dao.port.PortMapper;
import com.xinri.po.port.QisPort;
import com.xinri.service.port.IPortService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("portService")
public class PortService extends CrudService<PortMapper,QisPort> implements IPortService {

    //    JSONObject jsonObject = new JSONObject();
//        jsonObject.put("MessageContent",message);
//        jsonObject.put("UserNumber",moblie);
//        jsonObject.put("loginName","einvoice");
//        jsonObject.put("password","123456");
    @Override
    public String sendPort(String portName, String getParam, JSONObject postParam){
        QisPort port=new QisPort();
        port=dao.findByPortName(portName);
        String result="";
        try {
            if(port!=null){
                if(port.getPortMod().equals("get")){
                    JSONArray aa=new JSONArray();
                    aa= HttpRequestUtils.httpRequestGet(port.getUrl(),port.getTimeout());
                    result=aa+"";
                }else{
                    result= HttpRequestUtils.doPostByJson3(port.getUrl(),postParam,port.getTimeout());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }




}
