package com.xinri.service.roleLog;

import com.app.api.DataTable;
import com.xinri.po.roleLog.OperationRecord;

import java.util.Map;


public interface IRoleLogService {
    public DataTable<OperationRecord> findLogList(DataTable<OperationRecord> dt, Map<String, Object> searchParams);
}
