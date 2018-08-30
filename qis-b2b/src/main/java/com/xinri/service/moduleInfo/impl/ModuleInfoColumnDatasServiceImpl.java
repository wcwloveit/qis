package com.xinri.service.moduleInfo.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.dao.moduleInfo.ModuleInfoColumnDatasMapper;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:ModuleInfoColumnDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("moduleInfoColumnDatasService")
public class ModuleInfoColumnDatasServiceImpl extends CrudService<ModuleInfoColumnDatasMapper,ModuleInfoColumnDatas>  implements IModuleInfoColumnDatasService{


    @Override
    public DataTable<ModuleInfoColumnDatas> findListA(DataTable<ModuleInfoColumnDatas> dt, Map<String, Object> searchParams) {

        try {
            ModuleInfoColumnDatas moduleInfoColumnDatas = new ModuleInfoColumnDatas();
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            List<ModuleInfoColumnDatas> configList = new ArrayList<ModuleInfoColumnDatas>(); //new list

            //描述
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_descr") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_descr").toString().trim())) {
                    String descr = searchParams.get("ColumnData_descr").toString().trim();
                    moduleInfoColumnDatas.setStartCreatedOn(descr);
                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("ColumnData_startCreatedOn").toString().trim();
                    moduleInfoColumnDatas.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("RoleClass_endCreatedOn").toString().trim();
                    moduleInfoColumnDatas.setEndCreatedOn(endCreatedOn);
                }
            }

            moduleInfoColumnDatas.setPage(page);  //获取分页对象
            configList=dao.findList(moduleInfoColumnDatas); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }

    @Override
    public int insert(ModuleInfoColumnDatas moduleInfoColumnDatas) {
        return dao.insertSelective(moduleInfoColumnDatas);
    }

    @Override
    public List<Long> getColumnDataIds(Long id) {
        return dao.getColumnDataIds(id);
    }

    @Override
    public void relate(ModuleInfoColumnDatas moduleInfoColumnData) {
        dao.relate(moduleInfoColumnData);
    }

    @Override
    public Long getId(Long moduleId, Long ColumnDataId) {
        return dao.getId(moduleId, ColumnDataId);
    }

    @Override
    public Long[] getIdsByModuleId(Long moduleId) {
        return dao.getIdsByModuleId(moduleId);
    }

    @Override
    public Long[] getIdsByColumnDataId(Long ColumnDataId) {
        return dao.getIdsByColumnDataId(ColumnDataId);
    }

    @Override
    public Long[] getIds(Long moduleId, List<Long> ids) {
        return dao.getIds(ids, moduleId);
    }

    @Override
    public void deleteByColumnDataId(Long ColumnDataId) {
        dao.deleteByColumnDataId(ColumnDataId);
    }

    @Override
    public void deleteByModuleId(Long moduleId) {
        dao.deleteByModuleId(moduleId);
    }


}