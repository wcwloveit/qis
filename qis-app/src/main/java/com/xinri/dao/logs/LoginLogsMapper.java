package com.xinri.dao.logs;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.logs.LoginLogs;
import com.xinri.vo.log.LoginLogsVo;

import java.util.List;

/**
 * 类名:LoginLogsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface LoginLogsMapper extends CrudDao<LoginLogs>{
 public List<LoginLogsVo> findListByVo(LoginLogsVo loginLogsVo);
}

