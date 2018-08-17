package com.xinri.service.user;
import com.qis.common.service.IBaseService;
import com.xinri.po.user.Users;
import com.xinri.vo.users.OAUsersVo;

import java.util.List;

/**
 * <p></p>
 * 类名:UsersService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IUsersService extends IBaseService<Users>{

    public void syncUsers(List<OAUsersVo> oaUsersVos);

}
