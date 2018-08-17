package com.xinri.service.baseData;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.po.logs.LoginLogs;

import java.util.Map;

/**
 * <p></p>
 * 类名:BaseDatasService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IBaseDatasService extends IBaseService<BaseDatas>{

    DataTable<BaseDatas> findListByVo(DataTable<BaseDatas> dt, Map<String, Object> searchParams);

}

