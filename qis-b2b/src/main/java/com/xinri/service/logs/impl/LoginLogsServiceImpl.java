package com.xinri.service.logs.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.logs.LoginLogs;
import com.xinri.dao.logs.LoginLogsMapper;
import com.xinri.service.logs.ILoginLogsService;
/**
 * <p></p>
 * 类名:LoginLogsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("loginLogsService")
public class LoginLogsServiceImpl extends CrudService<LoginLogsMapper,LoginLogs>  implements ILoginLogsService{


}