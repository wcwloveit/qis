package com.xinri.dao.port;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.port.QisPort;
import com.xinri.po.production.ProductionLines;

/**
 * 类名:ProductionLinesMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface PortMapper extends CrudDao<QisPort>{

  public QisPort findByPortName(String portName);
}

