package com.xinri.service.moduleInfo;

import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import com.xinri.vo.redis.Redis;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

public interface IModuleInfoesService extends IBaseService<ModuleInfoes> {
    List<JsTree> getTree();

    AjaxStatus deleteModule(Long id);

    DataTable<ModuleInfoes> getModulesForRole(DataTable<ModuleInfoes> dt, List<Long> ids);

    List<ModuleInfoes> findListBySysUserId(ModuleInfoes moduleInfoes,Long id);

    Redis getModulesByUserId(Long id);


}

