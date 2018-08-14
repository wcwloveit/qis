package com.xinri.service.logs.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.logs.LoginLogs;
import com.xinri.dao.logs.LoginLogsMapper;
import com.xinri.service.logs.ILoginLogsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:LoginLogsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("loginLogsService")
public class LoginLogsServiceImpl extends CrudService<LoginLogsMapper,LoginLogs>  implements ILoginLogsService{


    @Override
    public DataTable<LoginLogs> findLogList(DataTable<LoginLogs> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            LoginLogs vo = new LoginLogs();
            List<LoginLogs> configList = new ArrayList<LoginLogs>();
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