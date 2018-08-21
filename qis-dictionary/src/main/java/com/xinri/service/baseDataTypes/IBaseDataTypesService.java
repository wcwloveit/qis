package com.xinri.service.baseDataTypes;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.baseDataTypes.BaseDataTypes;

import java.util.Map;

/**
 * <p></p>
 * 类名:BaseDataTypesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IBaseDataTypesService extends IBaseService<BaseDataTypes>{
    DataTable<BaseDataTypes> findListByVo(DataTable<BaseDataTypes> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);

}

