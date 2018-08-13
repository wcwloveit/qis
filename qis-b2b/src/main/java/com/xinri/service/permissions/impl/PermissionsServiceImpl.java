package com.xinri.service.permissions.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.permissions.Permissions;
import com.xinri.dao.permissions.PermissionsMapper;
import com.xinri.service.permissions.IPermissionsService;
/**
 * <p></p>
 * 类名:PermissionsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("permissionsService")
public class PermissionsServiceImpl extends CrudService<PermissionsMapper,Permissions>  implements IPermissionsService{


}