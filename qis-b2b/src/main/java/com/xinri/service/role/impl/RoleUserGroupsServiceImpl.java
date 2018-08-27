package com.xinri.service.role.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.xinri.po.user.UserGroups;
import com.xinri.util.AjaxStatus;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.RoleUserGroups;
import com.xinri.dao.role.RoleUserGroupsMapper;
import com.xinri.service.role.IRoleUserGroupsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:RoleUserGroupsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleUserGroupsService")
public class RoleUserGroupsServiceImpl extends CrudService<RoleUserGroupsMapper, RoleUserGroups> implements IRoleUserGroupsService {

    @Override
    public DataTable QueryGroupNotInRoleIdList(DataTable<UserGroups> dt, Map<String, Object> searchParams, String roleId) {
        int pageNo = dt.pageNo() + 1; //0
        int pageSize = dt.getiDisplayLength(); //10
        String name = "", code = "", descr = "";
        if (searchParams != null && searchParams.size() != 0) {
            if (searchParams.containsKey("LIKE_name") && !Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())) {
                name = "%" + searchParams.get("LIKE_name").toString().trim().toUpperCase() + "%";
            }
            if (searchParams.containsKey("LIKE_code") && !Strings.isNullOrEmpty(searchParams.get("LIKE_code").toString().trim())) {
                code = "%" + searchParams.get("LIKE_code").toString().trim().toUpperCase() + "%";
            }
            if (searchParams.containsKey("LIKE_descr") && !Strings.isNullOrEmpty(searchParams.get("LIKE_descr").toString().trim())) {
                descr = "%" + searchParams.get("LIKE_descr").toString().trim().toUpperCase() + "%";
            }
        }
        List<UserGroups> list = new ArrayList<>();
        if (!Strings.isNullOrEmpty(roleId)) {
            list = dao.findGroupNotInRoleId(roleId,name,code,descr);
        }
        dt.setiTotalDisplayRecords(list.size());
        int begin = pageSize * (pageNo - 1);
        int end = pageSize * pageNo;
        if (begin > list.size()) {
            list = new ArrayList<>();
        } else if (end > list.size()) {
            list = list.subList(begin, list.size());
        } else {
            list = list.subList(begin, end);
        }
        dt.setAaData(list);
        return dt;
    }

    @Override
    public DataTable QueryUserByRoleIdList(DataTable<UserGroups> dt, Map<String, Object> searchParams, String roleId) {
        int pageNo = dt.pageNo() + 1; //0
        int pageSize = dt.getiDisplayLength(); //10
        String name = "", code = "", descr = "";
        if (searchParams != null && searchParams.size() != 0) {
            if (searchParams.containsKey("LIKE_name") && !Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())) {
                name = "%" + searchParams.get("LIKE_name").toString().trim().toUpperCase() + "%";
            }
            if (searchParams.containsKey("LIKE_code") && !Strings.isNullOrEmpty(searchParams.get("LIKE_code").toString().trim())) {
                code = "%" + searchParams.get("LIKE_code").toString().trim().toUpperCase() + "%";
            }
            if (searchParams.containsKey("LIKE_descr") && !Strings.isNullOrEmpty(searchParams.get("LIKE_descr").toString().trim())) {
                descr = "%" + searchParams.get("LIKE_descr").toString().trim().toUpperCase() + "%";
            }
        }
        List<UserGroups> list = new ArrayList<>();
        if (!Strings.isNullOrEmpty(roleId)) {
            list = dao.findGroupByRoleId(roleId,name,code,descr);
        }
        dt.setiTotalDisplayRecords(list.size());
        int begin = pageSize * (pageNo - 1);
        int end = pageSize * pageNo;
        if (begin > list.size()) {
            list = new ArrayList<>();
        } else if (end > list.size()) {
            list = list.subList(begin, list.size());
        } else {
            list = list.subList(begin, end);
        }
        dt.setAaData(list);
        return dt;
    }

    @Override
    public AjaxStatus JoinRole(Long roleId,Long groupId){
        AjaxStatus as=new AjaxStatus(true);
        RoleUserGroups roleUserGroup = new RoleUserGroups();
        roleUserGroup.setRoleId(roleId);
        roleUserGroup.setUserGroupId(groupId);
        try {
            dao.insertSelective(roleUserGroup);
        } catch (Exception e) {
            as.setSuccess(false);
        }
        return as;
    }

    @Override
    public AjaxStatus LeaveRole(Long roleId,Long groupId){
        AjaxStatus as=new AjaxStatus(true);
        RoleUserGroups roleUserGroup = new RoleUserGroups();
        roleUserGroup.setRoleId(roleId);
        roleUserGroup.setUserGroupId(groupId);
        try {
            dao.removeByEntity(roleUserGroup);
        } catch (Exception e) {
            as.setSuccess(false);
        }
        return as;
    }
}