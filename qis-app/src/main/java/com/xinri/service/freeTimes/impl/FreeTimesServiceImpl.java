package com.xinri.service.freeTimes.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.freeTimes.FreeTimes;
import com.xinri.dao.freeTimes.FreeTimesMapper;
import com.xinri.service.freeTimes.IFreeTimesService;
/**
 * <p></p>
 * 类名:FreeTimesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("freeTimesService")
public class FreeTimesServiceImpl extends CrudService<FreeTimesMapper,FreeTimes>  implements IFreeTimesService{


}