package com.xinri.service.moduleInfo;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.vo.moduleInfo.RoleModuleInFoPerVo;

import java.util.List;
import java.util.Map;
/**
 * <p></p>
 * 类名:ModuleInfoColumnDatasService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IModuleInfoColumnDatasService extends IBaseService<ModuleInfoColumnDatas>{
    int insert(ModuleInfoColumnDatas moduleInfoColumnDatas);

    List<Long> getColumnDataIds(Long id);

    void relate(ModuleInfoColumnDatas moduleInfoColumnData);

    Long getId(Long moduleId, Long columnDataId);

    Long[] getIdsByModuleId(Long moduleId);

    Long[] getIdsByColumnDataId(Long columnDataId);

    Long[] getIds(Long moduleId, List<Long> ids);

    void deleteByColumnDataId(Long columnDataId);

    void deleteByModuleId(Long moduleId);

    DataTable<ModuleInfoColumnDatas> findListA(DataTable<ModuleInfoColumnDatas> dt, Map<String, Object> searchParams);

    List<RoleModuleInFoPerVo> getRoleModuleInFoColumnVo(RoleModuleInFoPerVo vo);
}

