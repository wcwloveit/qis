package com.xinri.service.baseData.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.dao.baseDataTypes.BaseDataTypesMapper;
import com.xinri.po.baseDataTypes.BaseDataTypes;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.dao.baseData.BaseDatasMapper;
import com.xinri.service.baseData.IBaseDatasService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("baseDatasService")
public class BaseDatasServiceImpl extends CrudService<BaseDatasMapper, BaseDatas> implements IBaseDatasService {

    @Autowired
    BaseDataTypesMapper baseDataTypesMapper;

    @Autowired
    BaseDatasMapper baseDatasMapper;

    @Override
    public List<JsTree> getTree(Long id) {
        List<JsTree> jsTrees = new ArrayList<>();
        BaseDatas baseData = new BaseDatas();
        baseData.setBaseDataTypeId(id);
        List<BaseDatas> baseDatas = baseDatasMapper.findList(baseData);
        for (int i = 0; i < baseDatas.size(); i++) {
            baseData = baseDatas.get(i);
            JsTree jsTree = new JsTree();
            if (baseData.getParentBaseDataId() == 0) {
                jsTree.setId(baseData.getId());
                jsTree.setParent("#");
                jsTree.setIcon("fa fa-home");
                jsTree.setText(baseData.getName());
            } else {
                jsTree.setId(baseData.getId());
                jsTree.setParent(baseData.getParentBaseDataId().toString().trim());
                jsTree.setIcon("glyphicon glyphicon-tint");
                jsTree.setText(baseData.getName());
            }
            jsTrees.add(jsTree);
        }
        return jsTrees;
    }

    @Override
    public List<BaseDatas> findParents(Long id) {
        BaseDatas baseData = new BaseDatas();
        baseData.setBaseDataTypeId(id);
        return baseDatasMapper.findList(baseData);
    }

    public AjaxStatus DeleteDic(Long id) {
        AjaxStatus status = new AjaxStatus(true);
        //是否为类，以及类下是否有引用
        BaseDatas baseData = dao.get(id);
        if (baseData != null && baseData.getParentBaseDataId() == 0) {
            baseData = new BaseDatas();
            baseData.setParentBaseDataId(id);
            List<BaseDatas> baseDatas = dao.findList(baseData);
            if (baseDatas.isEmpty()) {
                //删除
                dao.remove(id);
                status.setSuccess(true);
            } else {
                status.setSuccess(false);
                status.setMessage("该类被引用，不可删除");
            }
        } else if (baseData != null && baseData.getParentBaseDataId() != 0) {
            dao.remove(id);
            status.setSuccess(true);
        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        return status;
    }
}