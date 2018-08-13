package com.xinri.service.role.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.RoleUserGroups;
import com.xinri.dao.role.RoleUserGroupsMapper;
import com.xinri.service.role.IRoleUserGroupsService;
/**
 * <p></p>
 * 类名:RoleUserGroupsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleUserGroupsService")
public class RoleUserGroupsServiceImpl extends CrudService<RoleUserGroupsMapper,RoleUserGroups>  implements IRoleUserGroupsService{


}