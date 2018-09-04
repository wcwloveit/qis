package com.xinri.service.role.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.QisRoleMenu;
import com.xinri.dao.role.QisRoleMenuMapper;
import com.xinri.service.role.IQisRoleMenuService;
/**
 * <p></p>
 * 类名:QisRoleMenuServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("qisRoleMenuService")
public class QisRoleMenuServiceImpl extends CrudService<QisRoleMenuMapper,QisRoleMenu>  implements IQisRoleMenuService{


}