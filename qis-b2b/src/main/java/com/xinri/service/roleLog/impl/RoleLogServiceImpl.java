package com.xinri.service.roleLog.impl;


import com.qis.common.persistence.Page;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.xinri.dao.roleLog.RoleLogMapper;
import com.xinri.po.roleLog.OperationRecord;
import com.xinri.service.roleLog.IRoleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("roleLogService")
public class RoleLogServiceImpl implements IRoleLogService {

    @Autowired
    private RoleLogMapper dao;

    @Override
    public DataTable<OperationRecord> findLogList(DataTable<OperationRecord> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            OperationRecord po = new OperationRecord();

            List<OperationRecord> configList = new ArrayList<OperationRecord>();

            if ( searchParams!= null && searchParams.size() != 0) {
                //活动标题
                if (searchParams.containsKey("code")
                        &&!Strings.isNullOrEmpty(searchParams.get("code").toString().trim())) {
                    String code = searchParams.get("code").toString().trim();
                    po.setCode(code);
                }

                //车型名称
                if (searchParams.containsKey("name")
                        &&!Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    po.setName(name);
                }


            }


            po.setPage(page);

            configList = dao.findOperationList(po);
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();

        }
        return dt;
    }
}
