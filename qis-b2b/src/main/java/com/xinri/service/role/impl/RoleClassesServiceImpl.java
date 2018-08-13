package com.xinri.service.role.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.RoleClasses;
import com.xinri.dao.role.RoleClassesMapper;
import com.xinri.service.role.IRoleClassesService;
/**
 * <p></p>
 * 类名:RoleClassesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleClassesService")
public class RoleClassesServiceImpl extends CrudService<RoleClassesMapper,RoleClasses>  implements IRoleClassesService{


}