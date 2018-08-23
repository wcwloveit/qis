package com.xinri.service.moduleInfo.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.permissionsToModule.ModuleInfoPermissions;
import com.xinri.dao.permissionsToModule.ModuleInfoPermissionsMapper;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;
/**
 * <p></p>
 * 类名:ModuleInfoPermissionsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("moduleInfoPermissionsService")
public class ModuleInfoPermissionsServiceImpl extends CrudService<ModuleInfoPermissionsMapper,ModuleInfoPermissions>  implements IModuleInfoPermissionsService{


}