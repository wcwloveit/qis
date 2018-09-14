package com.xinri.service.moduleInfo.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataHeads;
import com.xinri.dao.moduleInfo.RoleModuleInfoColumnDataHeadsMapper;
import com.xinri.service.moduleInfo.IRoleModuleInfoColumnDataHeadsService;

import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoColumnDataHeadsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleModuleInfoColumnDataHeadsService")
public class RoleModuleInfoColumnDataHeadsServiceImpl extends CrudService<RoleModuleInfoColumnDataHeadsMapper,RoleModuleInfoColumnDataHeads>  implements IRoleModuleInfoColumnDataHeadsService{
    @Override
    public List<Long> getIds(Long id) {
        return dao.getIds(id);
    }

    @Override
    public List<Long> getColIds(Long moduleId, Long roleId) {
        return dao.getColIds(moduleId, roleId);
    }

    @Override
    public void celar(Long moduleId, Long roleId) {
        dao.celar(moduleId, roleId);
    }

    @Override
    public void deleteByRelateId(List<Long> ids) {
        dao.deleteByRelateId(ids);
    }

    @Override
    public void deleteByDiff(List<Long> ids,Long moduleId){
        dao.deleteByDiff(ids,moduleId);
    }

    @Override
    public List<Long> getIdsByDiff(List<Long> ids) {
        return dao.getIdsByDiff(ids);
    }

    @Override
    public List<Long> getBeforeIds(Long roleId, Long moduleId) {
        return dao.getBeforeIds(roleId,moduleId);
    }

    @Override
    public List<Long> getAfterIds(Long roleId, List<Long> afterModuleColumnDataIds) {
        return dao.getAfterIds(roleId,afterModuleColumnDataIds);
    }

}