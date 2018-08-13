package com.xinri.service.moduleInfo.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.dao.moduleInfo.ModuleInfoColumnDatasMapper;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;
/**
 * <p></p>
 * 类名:ModuleInfoColumnDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("moduleInfoColumnDatasService")
public class ModuleInfoColumnDatasServiceImpl extends CrudService<ModuleInfoColumnDatasMapper,ModuleInfoColumnDatas>  implements IModuleInfoColumnDatasService{


}