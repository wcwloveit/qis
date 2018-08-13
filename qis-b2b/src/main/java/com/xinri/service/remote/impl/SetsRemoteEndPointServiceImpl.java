package com.xinri.service.remote.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.remote.SetsRemoteEndPoint;
import com.xinri.dao.remote.SetsRemoteEndPointMapper;
import com.xinri.service.remote.ISetsRemoteEndPointService;
/**
 * <p></p>
 * 类名:SetsRemoteEndPointServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("setsRemoteEndPointService")
public class SetsRemoteEndPointServiceImpl extends CrudService<SetsRemoteEndPointMapper,SetsRemoteEndPoint>  implements ISetsRemoteEndPointService{


}