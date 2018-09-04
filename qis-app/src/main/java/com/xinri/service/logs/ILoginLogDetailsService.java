package com.xinri.service.logs;

import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.logs.LoginLogs;
import com.xinri.vo.log.LoginLogsVo;

import java.util.Map;

public interface ILoginLogDetailsService extends IBaseService<LoginLogs> {

    DataTable<LoginLogsVo> findListByVo(DataTable<LoginLogsVo> dt, Map<String, Object> searchParams);

}
