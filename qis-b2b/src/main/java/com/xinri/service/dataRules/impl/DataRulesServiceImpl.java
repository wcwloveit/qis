package com.xinri.service.dataRules.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.dataRules.DataRules;
import com.xinri.dao.dataRules.DataRulesMapper;
import com.xinri.service.dataRules.IDataRulesService;
/**
 * <p></p>
 * 类名:DataRulesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("dataRulesService")
public class DataRulesServiceImpl extends CrudService<DataRulesMapper,DataRules>  implements IDataRulesService{


}