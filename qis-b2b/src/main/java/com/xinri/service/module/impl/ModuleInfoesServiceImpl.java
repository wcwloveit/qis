package com.xinri.service.module.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.module.ModuleInfoes;
import com.xinri.dao.module.ModuleInfoesMapper;
import com.xinri.service.module.IModuleInfoesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:ModuleInfoesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("moduleInfoesService")
public class ModuleInfoesServiceImpl extends CrudService<ModuleInfoesMapper,ModuleInfoes>  implements IModuleInfoesService{

    @Override
    public List<JsTree> getTree() {
        List<JsTree> jsTrees = new ArrayList<>();
        ModuleInfoes moduleInfo=new ModuleInfoes();
        moduleInfo.setIsDeleted(0);
        List<ModuleInfoes> moduleInfoes = dao.findList(moduleInfo);
        for (int i = 0; i < moduleInfoes.size(); i++) {
             moduleInfo = moduleInfoes.get(i);
            JsTree jsTree = new JsTree();
            if (moduleInfo.getParentModuleId() == 0) {
                jsTree.setId(moduleInfo.getId());
                jsTree.setParent("#");
                jsTree.setIcon("fa fa-home");
                jsTree.setText(moduleInfo.getName());
            } else {
                jsTree.setId(moduleInfo.getId());
                jsTree.setParent(moduleInfo.getParentModuleId().toString().trim());
                jsTree.setIcon("glyphicon glyphicon-tint");
                jsTree.setText(moduleInfo.getName());
            }
            jsTrees.add(jsTree);
        }
        return jsTrees;
    }


    public AjaxStatus deleteModule(Long id) {
        AjaxStatus status = new AjaxStatus(true);
        //是否为菜单，以及菜单下是否有引用
        ModuleInfoes moduleInfo = dao.get(id);
        if (moduleInfo != null && moduleInfo.getParentModuleId() ==0) {
            moduleInfo = new ModuleInfoes();
            moduleInfo.setParentModuleId(id);
            moduleInfo.setIsDeleted(0);
            List<ModuleInfoes> moduleInfoes = dao.findList(moduleInfo);
            if (moduleInfoes.isEmpty()) {
                //删除
                dao.delete(id);
                status.setSuccess(true);
            } else {
                status.setSuccess(false);
                status.setMessage("该模块被引用，不可删除");
            }
        } else if (moduleInfo != null && moduleInfo.getParentModuleId() !=0) {
            dao.delete(id);
            status.setSuccess(true);
        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        return status;
    }
}