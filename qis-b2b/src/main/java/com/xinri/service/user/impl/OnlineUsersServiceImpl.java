package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.OnlineUsers;
import com.xinri.dao.user.OnlineUsersMapper;
import com.xinri.service.user.IOnlineUsersService;
/**
 * <p></p>
 * 类名:OnlineUsersServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("onlineUsersService")
public class OnlineUsersServiceImpl extends CrudService<OnlineUsersMapper,OnlineUsers>  implements IOnlineUsersService{


}