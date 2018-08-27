package com.xinri.service.moduleInfo;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.vo.columnData.ColumnDataVo;

import java.util.Map;

/**
 * <p></p>
 * 类名:ColumnDatasService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IColumnDatasService extends IBaseService<ColumnDatas>{
    public DataTable<ColumnDataVo> findListByVo
            (DataTable<ColumnDataVo>dt, Map<String, Object> searchParams);

}

