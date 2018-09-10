package com.xinri.service.logs;

import com.app.api.DataTable;
import com.xinri.po.logs.ErrorLogs;

import java.util.Map;

public interface IErrorLogsService {
    DataTable<ErrorLogs> findLogList(DataTable<ErrorLogs> dt, Map<String, Object> searchParams);
}
