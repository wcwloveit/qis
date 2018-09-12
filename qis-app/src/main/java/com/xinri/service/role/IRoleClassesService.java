package com.xinri.service.role;
import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.role.RoleClasses;
import com.xinri.vo.role.RoleClassesVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:RoleClassesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRoleClassesService extends IBaseService<RoleClasses>{

    public DataTable<RoleClassesVo>  findListByVo( DataTable<RoleClassesVo>dt, Map<String, Object> searchParams);

    public void exportExcel(HttpServletResponse response, Map<String, Object> searchParams);
}

