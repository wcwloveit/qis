package com.xinri.service.moduleInfo.impl;
import com.xinri.service.moduleInfo.IColumnDatasService;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.dao.moduleInfo.ColumnDatasMapper;

/**
 * <p></p>
 * 类名:ColumnDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("columnDatasService")
public class ColumnDatasServiceImpl extends CrudService<ColumnDatasMapper,ColumnDatas>  implements IColumnDatasService {


}