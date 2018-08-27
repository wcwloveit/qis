package com.xinri.service.roleModulePermissions.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.roleModulePermissions.RoleModuleInfoPermissionHeads;
import com.xinri.dao.roleModulePermissions.RoleModuleInfoPermissionHeadsMapper;
import com.xinri.service.roleModulePermissions.IRoleModuleInfoPermissionHeadsService;

import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoPermissionHeadsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleModuleInfoPermissionHeadsService")
public class RoleModuleInfoPermissionHeadsServiceImpl extends CrudService<RoleModuleInfoPermissionHeadsMapper,RoleModuleInfoPermissionHeads>  implements IRoleModuleInfoPermissionHeadsService{


    @Override
    public List<Long> getIds(Long id) {
        return dao.getIds(id);
    }

    @Override
    public List<Long> getPerIds(Long moduleId, Long roleId) {
        return dao.getPerIds(moduleId,roleId);
    }
}