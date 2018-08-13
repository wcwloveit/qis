package com.xinri.service.user.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.QisUserRole;
import com.xinri.dao.user.QisUserRoleMapper;
import com.xinri.service.user.IQisUserRoleService;
/**
 * <p></p>
 * 类名:QisUserRoleServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("qisUserRoleService")
public class QisUserRoleServiceImpl extends CrudService<QisUserRoleMapper,QisUserRole>  implements IQisUserRoleService{


}