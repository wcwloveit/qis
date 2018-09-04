package com.xinri.dao.role;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.role.RoleUserGroups;
import com.xinri.po.user.UserGroups;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 类名:RoleUserGroupsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface RoleUserGroupsMapper extends CrudDao<RoleUserGroups>{
    List<UserGroups> findGroupNotInRoleId(@Param("roleId") String roleId, @Param("name") String name, @Param("code") String code, @Param("descr") String descr);

    List<UserGroups> findGroupByRoleId(@Param("roleId") String roleId, @Param("name") String name, @Param("code") String code, @Param("descr") String descr);
}

