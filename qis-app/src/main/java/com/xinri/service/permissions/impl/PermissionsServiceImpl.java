package com.xinri.service.permissions.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.permissions.Permissions;
import com.xinri.dao.permissions.PermissionsMapper;
import com.xinri.service.permissions.IPermissionsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("permissionsService")
public class PermissionsServiceImpl extends CrudService<PermissionsMapper, Permissions> implements IPermissionsService {

    @Override
    public DataTable<Permissions> findList(DataTable<Permissions> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo() + 1, dt.getiDisplayLength());
            Permissions permissions = new Permissions();  //实体类
            List<Permissions> configList = new ArrayList<Permissions>(); //new list
            if (searchParams != null && searchParams.size() != 0) {
                //名称
                if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    permissions.setName(String.valueOf(name));
                }
                //编码
                if (searchParams.containsKey("code") && !Strings.isNullOrEmpty(searchParams.get("code").toString().trim())) {
                    String code = searchParams.get("code").toString().trim();
                    permissions.setCode(code);
                }
                //描述
                if (searchParams.containsKey("descr") && !Strings.isNullOrEmpty(searchParams.get("descr").toString().trim())) {
                    String descr = searchParams.get("descr").toString().trim();
                    permissions.setDescr(String.valueOf(descr));
                }
                //创建时间 开始日期
                if (searchParams.containsKey("startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("startCreatedOn").toString().trim();
                    permissions.setStartCreatedOn(startCreatedOn);
                }
                //创建时间 结束日期
                if (searchParams.containsKey("endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("endCreatedOn").toString().trim();
                    permissions.setEndCreatedOn(endCreatedOn);
                }
            }
            permissions.setPage(page);  //获取分页对象
            configList = dao.findList(permissions); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错" + e.getMessage());
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

    @Override
    public List<Permissions> getPermissionsByModuleIdandRoleId(List<Long> list, Long moduleId) {
        return dao.getPermissionsByModuleIdandRoleId(list,moduleId);
    }

}