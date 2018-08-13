package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.SysUser;
import com.xinri.dao.user.SysUserMapper;
import com.xinri.service.user.ISysUserService;
/**
 * <p>管理员表</p>
 * 类名:SysUserServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("sysUserService")
public class SysUserServiceImpl extends CrudService<SysUserMapper,SysUser>  implements ISysUserService{


}