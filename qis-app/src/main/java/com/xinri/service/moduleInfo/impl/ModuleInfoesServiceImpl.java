package com.xinri.service.moduleInfo.impl;

import com.app.api.DataTable;
import com.qis.common.persistence.Page;

import com.xinri.po.baseData.BaseDatas;
import com.xinri.po.role.Roles;
import com.xinri.util.AjaxStatus;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import com.xinri.vo.redis.Redis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.dao.moduleInfo.ModuleInfoesMapper;
import com.xinri.service.moduleInfo.IModuleInfoesService;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("moduleInfoesService")
public class ModuleInfoesServiceImpl extends CrudService<ModuleInfoesMapper, ModuleInfoes> implements IModuleInfoesService {

    @Override
    public List<JsTree> getTree() {
        List<JsTree> jsTrees = new ArrayList<>();
        ModuleInfoes moduleInfo = new ModuleInfoes();
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
            if (moduleInfo.getIcon() != null && !"".equals(moduleInfo.getIcon())) {
                jsTree.setIcon(moduleInfo.getIcon());
            }
            jsTrees.add(jsTree);
        }
        return jsTrees;
    }


    public AjaxStatus deleteModule(Long id) {
        AjaxStatus status = new AjaxStatus(true);
        //是否为菜单，以及菜单下是否有引用
        ModuleInfoes moduleInfo = dao.get(id);
        if (moduleInfo != null && moduleInfo.getParentModuleId() == 0) {
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
        } else if (moduleInfo != null && moduleInfo.getParentModuleId() != 0) {
            dao.delete(id);
            status.setSuccess(true);
        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        return status;
    }

    @Override
    public DataTable<ModuleInfoes> getModulesForRole(DataTable<ModuleInfoes> dt, List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            try {
                Page page = new Page(dt.pageNo() + 1, dt.getiDisplayLength());
                List<ModuleInfoes> moduleInfoes = new ArrayList<ModuleInfoes>(); //new list
                moduleInfoes = dao.getModulesForRole(ids); //获取分页数据
                page.setData(moduleInfoes);  //***
                dt.setiTotalDisplayRecords(page.getTotalSize());
                dt.setAaData(page.getData());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("配置列表出错" + e.getMessage());
            }
            return dt;
        } else {
            return null;
        }
    }

    @Override
    public List<ModuleInfoes> findListBySysUserId(ModuleInfoes moduleInfoes, Long id) {
        return dao.findList(moduleInfoes);
    }

    @Override
    public Redis getModulesByUserId(Long userId,Long deptId) {
        return dao.getModulesByUserId(userId,deptId);
    }

    @Override
    public Redis getModulesBySysUserId(Long id) {
        return dao.getModulesBySysUserId(id);
    }
}