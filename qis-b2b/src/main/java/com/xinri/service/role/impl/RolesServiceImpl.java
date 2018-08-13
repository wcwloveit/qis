package com.xinri.service.role.impl;
import com.xinri.dao.role.RolesMapper;
import com.xinri.po.role.Roles;
import com.xinri.service.role.IRolesService;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;

/**
 * <p></p>
 * 类名:RolesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("rolesService")
public class RolesServiceImpl extends CrudService<RolesMapper,Roles>  implements IRolesService {


}