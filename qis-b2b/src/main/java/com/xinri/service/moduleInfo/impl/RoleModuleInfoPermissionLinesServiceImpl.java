package com.xinri.service.moduleInfo.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;
import com.xinri.dao.moduleInfo.RoleModuleInfoPermissionLinesMapper;
import com.xinri.service.moduleInfo.IRoleModuleInfoPermissionLinesService;
/**
 * <p></p>
 * 类名:RoleModuleInfoPermissionLinesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleModuleInfoPermissionLinesService")
public class RoleModuleInfoPermissionLinesServiceImpl extends CrudService<RoleModuleInfoPermissionLinesMapper,RoleModuleInfoPermissionLines>  implements IRoleModuleInfoPermissionLinesService{


}