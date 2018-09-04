package com.xinri.service.logs;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.logs.LoginLogs;

import java.util.Map;
/**
 * <p></p>
 * 类名:LoginLogsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface ILoginLogsService extends IBaseService<LoginLogs>{


    DataTable<LoginLogs> findLogList(DataTable<LoginLogs> dt, Map<String, Object> searchParams);
}

