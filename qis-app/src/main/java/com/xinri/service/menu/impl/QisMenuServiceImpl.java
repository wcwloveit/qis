package com.xinri.service.menu.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.menu.QisMenu;
import com.xinri.dao.menu.QisMenuMapper;
import com.xinri.service.menu.IQisMenuService;
/**
 * <p></p>
 * 类名:QisMenuServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("qisMenuService")
public class QisMenuServiceImpl extends CrudService<QisMenuMapper,QisMenu>  implements IQisMenuService{


}