package com.xinri.service.user;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.user.SysUser;
import com.xinri.vo.users.SysUserVo;

import java.util.Map;

/**
 * <p>管理员表</p>
 * 类名:SysUserService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface ISysUserService extends IBaseService<SysUser>{

    DataTable<SysUser> findList(DataTable<SysUser> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);

    public DataTable<SysUserVo> findListByDt(DataTable<SysUserVo> dt, Map<String, Object> searchParams);

}

