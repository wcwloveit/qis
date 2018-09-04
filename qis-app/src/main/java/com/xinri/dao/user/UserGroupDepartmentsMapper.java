package com.xinri.dao.user;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.departments.Departments;
import com.xinri.po.user.UserGroupDepartments;
import com.xinri.vo.departments.DepartmentsVo;

import java.util.List;

/**
 * 类名:UserGroupDepartmentsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface UserGroupDepartmentsMapper extends CrudDao<UserGroupDepartments>{

 public List<Departments> getDepartmentByUserGroupsId(DepartmentsVo departmentsVo);

 public List<Departments> getNotDepartmentByUserGroupsId(DepartmentsVo departmentsVo);

}

