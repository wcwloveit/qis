package com.xinri.service.module;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.module.ModuleInfoes;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:ModuleInfoesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IModuleInfoesService extends IBaseService<ModuleInfoes>{
    List<JsTree> getTree();

    public AjaxStatus deleteModule(Long id);

}

