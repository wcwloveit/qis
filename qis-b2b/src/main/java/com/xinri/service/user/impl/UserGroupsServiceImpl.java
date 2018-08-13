package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.UserGroups;
import com.xinri.dao.user.UserGroupsMapper;
import com.xinri.service.user.IUserGroupsService;
/**
 * <p></p>
 * 类名:UserGroupsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("userGroupsService")
public class UserGroupsServiceImpl extends CrudService<UserGroupsMapper,UserGroups>  implements IUserGroupsService{


}