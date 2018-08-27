package com.xinri.service.baseDataTypes;

import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.baseDataTypes.BaseDataTypes;

import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IBaseDataTypesService extends IBaseService<BaseDataTypes> {
    DataTable<BaseDataTypes> findListByVo(DataTable<BaseDataTypes> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);

}

