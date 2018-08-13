package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.Users;
import com.xinri.dao.user.UsersMapper;
import com.xinri.service.user.IUsersService;
/**
 * <p></p>
 * 类名:UsersServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("usersService")
public class UsersServiceImpl extends CrudService<UsersMapper,Users>  implements IUsersService{


}