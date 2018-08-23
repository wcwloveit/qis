package com.xinri.service.role.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.dao.role.RolesMapper;
import com.xinri.po.role.Roles;
import com.xinri.service.role.IRolesService;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:RolesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("rolesService")
public class RolesServiceImpl extends CrudService<RolesMapper,Roles>  implements IRolesService {

    @Override
    public DataTable<Roles> findListByVo(DataTable<Roles> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            Roles roles= new Roles();  //实体类
            List<Roles> configList = new ArrayList<Roles>(); //new list

            if ( searchParams!= null && searchParams.size() != 0) {
                //角色名称
                if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    roles.setName(String.valueOf(name));
                }
                //角色类型
                if (searchParams.containsKey("role_class_id") && !Strings.isNullOrEmpty(searchParams.get("role_class_id").toString().trim())) {
                    Long roleClassId = (Long)searchParams.get("role_class_id");
                    roles.setRoleClassId(roleClassId);
                }
                //描述
                if (searchParams.containsKey("desc") && !Strings.isNullOrEmpty(searchParams.get("desc").toString().trim())) {
                    String desc = searchParams.get("desc").toString().trim();
                    roles.setDescr(String.valueOf(desc));
                }
                //创建时间 开始日期
                if (searchParams.containsKey("startCreatedOn")&&!Strings.isNullOrEmpty(searchParams.get("startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("startCreatedOn").toString().trim();
                    roles.setStartCreatedOn(startCreatedOn);
                }
                //创建时间 结束日期
                if (searchParams.containsKey("endCreatedOn")&&!Strings.isNullOrEmpty(searchParams.get("endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("endCreatedOn").toString().trim();
                    roles.setEndCreatedOn(endCreatedOn);
                }
            }
//
//            if ( searchParams!= null && searchParams.size() != 0) {
//                if (searchParams.containsKey("BaseData_effectiveDateEnd") && !Strings.isNullOrEmpty(searchParams.get("BaseData_effectiveDateEnd").toString().trim())) {
//                    String effectiveDateEnd = searchParams.get("BaseData_effectiveDateEnd").toString().trim();
//                    roles.setEffectiveDateStart(Date.valueOf(effectiveDateEnd));
//                }
//            }

            roles.setPage(page);  //获取分页对象
            configList=dao.findList(roles); //获取分页数据
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
        }catch (Exception e) {
            logger.error("删除出错了"+e.getMessage());
        }
        return statu;
    }

}