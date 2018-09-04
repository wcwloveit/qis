package com.xinri.service.role.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.QisRole;
import com.xinri.dao.role.QisRoleMapper;
import com.xinri.service.role.IQisRoleService;
/**
 * <p></p>
 * 类名:QisRoleServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("qisRoleService")
public class QisRoleServiceImpl extends CrudService<QisRoleMapper,QisRole>  implements IQisRoleService{


}