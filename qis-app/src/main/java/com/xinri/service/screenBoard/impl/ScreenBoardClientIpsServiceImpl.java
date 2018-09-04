package com.xinri.service.screenBoard.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.screenBoard.ScreenBoardClientIps;
import com.xinri.dao.screenBoard.ScreenBoardClientIpsMapper;
import com.xinri.service.screenBoard.IScreenBoardClientIpsService;
/**
 * <p></p>
 * 类名:ScreenBoardClientIpsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("screenBoardClientIpsService")
public class ScreenBoardClientIpsServiceImpl extends CrudService<ScreenBoardClientIpsMapper,ScreenBoardClientIps>  implements IScreenBoardClientIpsService{


}