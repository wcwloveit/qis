package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.UserUserGroups;
import com.xinri.dao.user.UserUserGroupsMapper;
import com.xinri.service.user.IUserUserGroupsService;
/**
 * <p></p>
 * 类名:UserUserGroupsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("userUserGroupsService")
public class UserUserGroupsServiceImpl extends CrudService<UserUserGroupsMapper,UserUserGroups>  implements IUserUserGroupsService{


}