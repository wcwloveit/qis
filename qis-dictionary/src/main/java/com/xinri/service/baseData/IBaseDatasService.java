package com.xinri.service.baseData;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.po.baseDataTypes.BaseDataTypes;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;


import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:BaseDatasService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IBaseDatasService extends IBaseService<BaseDatas>{

    List<JsTree> getTree(Long id);

    List<BaseDatas> findParents(Long id);

    public AjaxStatus DeleteDic(Long id);

}

