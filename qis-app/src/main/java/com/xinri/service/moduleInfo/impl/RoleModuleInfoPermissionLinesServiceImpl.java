package com.xinri.service.moduleInfo.impl;
import com.app.api.DataTable;
import com.qis.common.persistence.Page;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;
import com.xinri.dao.moduleInfo.RoleModuleInfoPermissionLinesMapper;
import com.xinri.service.moduleInfo.IRoleModuleInfoPermissionLinesService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoPermissionLinesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleModuleInfoPermissionLinesService")
public class RoleModuleInfoPermissionLinesServiceImpl extends CrudService<RoleModuleInfoPermissionLinesMapper,RoleModuleInfoPermissionLines>  implements IRoleModuleInfoPermissionLinesService{


    @Override
    public DataTable<RoleModuleInFoPermissionLineVo> getModulesForRole(DataTable<RoleModuleInFoPermissionLineVo> dt, Long roleId, Long infoId) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            RoleModuleInFoPermissionLineVo vo = new RoleModuleInFoPermissionLineVo();  //实体类
            vo.setModuleId(infoId);
            vo.setRoleId(roleId);
            List<RoleModuleInFoPermissionLineVo> configList = new ArrayList<RoleModuleInFoPermissionLineVo>(); //new list
            vo.setPage(page);  //获取分页对象
            configList = dao.findListByVo(vo); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("角色模块权限 人员列表出错"+e.getMessage());
        }
        return dt;
    }

    @Override
    public List<Long> getIdsByDiff(List<Long> ids) {
        return dao.getIdsByDiff(ids);
    }
}