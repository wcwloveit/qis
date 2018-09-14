package com.xinri.service.moduleInfo;

import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IRoleModuleInfoPermissionHeadsService extends IBaseService<RoleModuleInfoPermissionHeads> {

    List<Long> getIds(Long id);

    List<Long> getPerIds(Long moduleId, Long roleId);

    void celar(Long moduleId,Long roleId);

    void deleteByRelateId(List<Long> ids);

    void deleteByDiff(List<Long> ids,Long moduleId);

    List<Long> getIdsByDiff(List<Long> ids);

    List<Long> getBeforeIds( Long roleId, Long moduleId);

    List<Long> getAfterIds(Long roleId,List<Long> ids);

}

