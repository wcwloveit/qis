package com.xinri.service.moduleInfo;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataHeads;

import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoColumnDataHeadsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleModuleInfoColumnDataHeadsService extends IBaseService<RoleModuleInfoColumnDataHeads>{

    List<Long> getIds(Long id);

    List<Long> getColIds(Long moduleId, Long roleId);

    void celar(Long moduleId,Long roleId);

    void deleteByRelateId(List<Long> ids);

    void deleteByDiff(List<Long> ids,Long moduleId);

    List<Long> getIdsByDiff(List<Long> ids);

    List<Long> getBeforeIds(Long roleId, Long moduleId);

    List<Long> getAfterIds(Long roleId, List<Long> afterModuleColumnDataIds);
}

