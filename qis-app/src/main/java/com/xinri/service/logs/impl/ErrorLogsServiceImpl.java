package com.xinri.service.logs.impl;

import com.app.api.DataTable;
import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.xinri.dao.logs.ErrorLogsMapper;
import com.xinri.po.logs.ErrorLogs;
import com.xinri.service.logs.IErrorLogsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorLogsServiceImpl extends CrudService<ErrorLogsMapper,ErrorLogs> implements IErrorLogsService {
    @Override
    public DataTable<ErrorLogs> findLogList(DataTable<ErrorLogs> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ErrorLogs vo = new ErrorLogs();
            List<ErrorLogs> configList = new ArrayList<ErrorLogs>();
//            if ( searchParams!= null && searchParams.size() != 0) {
//                if (searchParams.containsKey("ZC_isDeleted")&&!Strings.isNullOrEmpty(searchParams.get("ZC_isDeleted").toString().trim())) {
//                    String modelName = searchParams.get("ZC_isDeleted").toString().trim();
//                    vo.setIsDeleted(Integer.valueOf(modelName));
//                }
//
//            }
            vo.setPage(page);
            configList = dao.findList(vo);
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("活动列表出错"+e.getMessage());
        }
        return dt;
    }
}
