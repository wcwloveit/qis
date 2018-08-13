package com.xinri.service.moduleInfo.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.dao.moduleInfo.ModuleInfoesMapper;
import com.xinri.service.moduleInfo.IModuleInfoesService;
/**
 * <p></p>
 * 类名:ModuleInfoesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("moduleInfoesService")
public class ModuleInfoesServiceImpl extends CrudService<ModuleInfoesMapper,ModuleInfoes>  implements IModuleInfoesService{


}