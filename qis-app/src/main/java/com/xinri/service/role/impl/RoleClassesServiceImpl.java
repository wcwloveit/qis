package com.xinri.service.role.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.vo.role.RoleClassesVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.RoleClasses;
import com.xinri.dao.role.RoleClassesMapper;
import com.xinri.service.role.IRoleClassesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:RoleClassesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleClassesService")
public class RoleClassesServiceImpl extends CrudService<RoleClassesMapper,RoleClasses>  implements IRoleClassesService{


    @Override
    public  DataTable<RoleClassesVo>  findListByVo( DataTable<RoleClassesVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            RoleClassesVo roleClassesVo= new RoleClassesVo();  //实体类
            List<RoleClassesVo> configList = new ArrayList<RoleClassesVo>(); //new list

            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_name") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_name").toString().trim())) {
                    String name = searchParams.get("RoleClass_name").toString().trim();
                    roleClassesVo.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_code") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_code").toString().trim())) {
                    String code = searchParams.get("RoleClass_code").toString().trim();
                    roleClassesVo.setCode(String.valueOf(code));

                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("RoleClass_startCreatedOn").toString().trim();
                    roleClassesVo.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("RoleClass_endCreatedOn").toString().trim();
                    roleClassesVo.setEndCreatedOn(endCreatedOn);
                }
            }

            roleClassesVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(roleClassesVo); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }
}