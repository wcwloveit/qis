package com.xinri.dao.roleLog;

import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.roleLog.OperationRecord;

import java.util.List;

@MyBatisDao
public interface RoleLogMapper {

    public List<OperationRecord> findOperationList(OperationRecord po);

}
