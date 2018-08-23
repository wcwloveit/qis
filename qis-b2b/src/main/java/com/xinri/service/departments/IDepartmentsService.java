package com.xinri.service.departments;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.departments.Departments;
import com.xinri.vo.dept.OADepartmentVo;
import com.xinri.vo.org.OrgListVo;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:DepartmentsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IDepartmentsService extends IBaseService<Departments>{

    public void syncOADept(List<OADepartmentVo> deptList);

    public List<Departments> getUserDept(Departments departments);

    public DataTable<OrgListVo> getDeptList(Map<String, Object> searchParams, String id, DataTable<OrgListVo> dt);
}

