package com.xinri.service.moduleInfo.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.service.moduleInfo.IColumnDatasService;
import com.xinri.vo.columnData.ColumnDataVo;
import com.xinri.vo.role.RoleClassesVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.dao.moduleInfo.ColumnDatasMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:ColumnDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("columnDatasService")
public class ColumnDatasServiceImpl extends CrudService<ColumnDatasMapper,ColumnDatas>  implements IColumnDatasService {


    @Override
    public DataTable<ColumnDataVo> findListByVo(DataTable<ColumnDataVo> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ColumnDataVo columnDataVo= new ColumnDataVo();  //实体类
            List<ColumnDataVo> configList = new ArrayList<ColumnDataVo>(); //new list
            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_name") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_name").toString().trim())) {
                    String name = searchParams.get("ColumnData_name").toString().trim();
                    columnDataVo.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_code") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_code").toString().trim())) {
                    String code = searchParams.get("ColumnData_code").toString().trim();
                    columnDataVo.setCode(String.valueOf(code));

                }
            }

            //描述
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_descr") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_descr").toString().trim())) {
                    String descr = searchParams.get("ColumnData_descr").toString().trim();
                    columnDataVo.setStartCreatedOn(descr);
                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ColumnData_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("ColumnData_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("ColumnData_startCreatedOn").toString().trim();
                    columnDataVo.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("RoleClass_endCreatedOn").toString().trim();
                    columnDataVo.setEndCreatedOn(endCreatedOn);
                }
            }

            columnDataVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(columnDataVo); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Boolean deleteOne(Long id) {
        boolean statu = false;
        try {
            dao.delete(id);
            statu = true;
        } catch (Exception e) {
            logger.error("删除出错了" + e.getMessage());
        }
        return statu;
    }

}