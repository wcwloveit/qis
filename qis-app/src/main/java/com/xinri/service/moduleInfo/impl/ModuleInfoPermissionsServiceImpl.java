package com.xinri.service.moduleInfo.impl;

import com.xinri.vo.moduleInfo.RoleModuleInFoPerVo;
import com.xinri.vo.redis.Resource;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.dao.moduleInfo.ModuleInfoPermissionsMapper;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("moduleInfoPermissionsService")
public class ModuleInfoPermissionsServiceImpl extends CrudService<ModuleInfoPermissionsMapper, ModuleInfoPermissions> implements IModuleInfoPermissionsService {
    @Override
    public int insert(ModuleInfoPermissions moduleInfoPermissions) {
        return dao.insertSelective(moduleInfoPermissions);
    }

    @Override
    public List<Long> getPermissionIds(Long id) {
        return dao.getPermissionIds(id);
    }

    @Override
    public void relate(ModuleInfoPermissions moduleInfoPermission) {
        dao.relate(moduleInfoPermission);
    }

    @Override
    public Long getId(Long moduleId, Long permissionId) {
        return dao.getId(moduleId, permissionId);
    }

    @Override
    public Long[] getIdsByModuleId(Long moduleId) {
        return dao.getIdsByModuleId(moduleId);
    }

    @Override
    public Long[] getIdsByPermissionId(Long permissionId) {
        return dao.getIdsByPermissionId(permissionId);
    }

    @Override
    public Long[] getIds(Long moduleId, List<Long> ids) {
        return dao.getIds(ids, moduleId);
    }

    @Override
    public void deleteByPermissionId(Long permissionId) {
        dao.deleteByPermissionId(permissionId);
    }

    @Override
    public void deleteByModuleId(Long moduleId) {
        dao.deleteByModuleId(moduleId);
    }

    @Override
    public List<RoleModuleInFoPerVo> getRoleModuleInFoPerVo(RoleModuleInFoPerVo vo) {
        return dao.getRoleModuleInFoPerVo(vo);
    }

    @Override
    public List<Resource> getResource() {
        List<Resource> resources=dao.getResource();
        String beforeUrl="";
        String afterUrl="";
        for (Resource resource : resources) {
            beforeUrl=resource.getUrl();
            afterUrl=beforeUrl.substring(0,beforeUrl.lastIndexOf("/"))+"/"+resource.getDescr();
            resource.setUrl(afterUrl);
            resource.setCode(resource.getMoCode()+"-"+resource.getPeCode());
        }
        return resources;
    }

}