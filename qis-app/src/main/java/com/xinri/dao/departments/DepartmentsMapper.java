package com.xinri.dao.departments;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.departments.Departments;

import java.util.List;

/**
 * 类名:DepartmentsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface DepartmentsMapper extends CrudDao<Departments>{

  public void insertDeptList(List<Departments> departments);

  public List<Departments> getUserDept(Departments departments);

  public List<Departments> findAllChildDept(String id);
}

