package com.xinri.service.moduleInfo;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;

import java.util.Map;
/**
 * <p></p>
 * 类名:ModuleInfoColumnDatasService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IModuleInfoColumnDatasService extends IBaseService<ModuleInfoColumnDatas>{


    DataTable<ModuleInfoColumnDatas> findListA(DataTable<ModuleInfoColumnDatas> dt, Map<String, Object> searchParams);
}

