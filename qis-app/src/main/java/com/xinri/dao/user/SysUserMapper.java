package com.xinri.dao.user;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.user.SysUser;
import com.xinri.vo.users.SysUserVo;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
 @MyBatisDao
public interface SysUserMapper extends CrudDao<SysUser>{
 List<SysUserVo> findListByVo(SysUserVo vo);
}

