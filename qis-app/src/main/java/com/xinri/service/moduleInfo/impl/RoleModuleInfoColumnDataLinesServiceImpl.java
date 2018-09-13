package com.xinri.service.moduleInfo.impl;
import com.app.api.DataTable;
import com.qis.common.persistence.Page;
import com.xinri.vo.moduleInfo.RoleModuleInFoColumnDataLineVo;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataLines;
import com.xinri.dao.moduleInfo.RoleModuleInfoColumnDataLinesMapper;
import com.xinri.service.moduleInfo.IRoleModuleInfoColumnDataLinesService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * 类名:RoleModuleInfoColumnDataLinesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleModuleInfoColumnDataLinesService")
public class RoleModuleInfoColumnDataLinesServiceImpl extends CrudService<RoleModuleInfoColumnDataLinesMapper,RoleModuleInfoColumnDataLines>  implements IRoleModuleInfoColumnDataLinesService{


    @Override
    public DataTable<RoleModuleInFoColumnDataLineVo> getModulesForRole(DataTable<RoleModuleInFoColumnDataLineVo> dt, Long roleId, Long infoId) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            RoleModuleInFoColumnDataLineVo vo = new RoleModuleInFoColumnDataLineVo();  //实体类
            vo.setModuleId(infoId);
            vo.setRoleId(roleId);
            List<RoleModuleInFoColumnDataLineVo> configList = new ArrayList<RoleModuleInFoColumnDataLineVo>(); //new list
            vo.setPage(page);  //获取分页对象
            configList = dao.findListByVo(vo); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("角色模块数据列 人员列表出错"+e.getMessage());
        }
        return dt;
    }

    @Override
    public List<Long> getIdsByDiff(List<Long> beforeIds) {
        return dao.getIdsByDiff(beforeIds);
    }

}