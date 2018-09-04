package com.xinri.service.permissions;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.permissions.Permissions;

import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IPermissionsService extends IBaseService<Permissions>{
    DataTable<Permissions> findList(DataTable<Permissions> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);


}

