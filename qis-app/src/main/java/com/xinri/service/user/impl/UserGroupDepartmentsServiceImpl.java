package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.UserGroupDepartments;
import com.xinri.dao.user.UserGroupDepartmentsMapper;
import com.xinri.service.user.IUserGroupDepartmentsService;
/**
 * <p></p>
 * 类名:UserGroupDepartmentsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("userGroupDepartmentsService")
public class UserGroupDepartmentsServiceImpl extends CrudService<UserGroupDepartmentsMapper,UserGroupDepartments>  implements IUserGroupDepartmentsService{


}