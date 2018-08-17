package com.xinri.service.departments;
import com.qis.common.service.IBaseService;
import com.xinri.po.departments.Departments;
import com.xinri.vo.dept.OADepartmentVo;

import java.util.List;

/**
 * <p></p>
 * 类名:DepartmentsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IDepartmentsService extends IBaseService<Departments>{

    public void syncOADept(List<OADepartmentVo> deptList);
}

