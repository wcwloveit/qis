package com.xinri.service.screenBoard.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.screenBoard.ScreenBoardDatas;
import com.xinri.dao.screenBoard.ScreenBoardDatasMapper;
import com.xinri.service.screenBoard.IScreenBoardDatasService;
/**
 * <p></p>
 * 类名:ScreenBoardDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("screenBoardDatasService")
public class ScreenBoardDatasServiceImpl extends CrudService<ScreenBoardDatasMapper,ScreenBoardDatas>  implements IScreenBoardDatasService{


}