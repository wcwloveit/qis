package com.xinri.service.role;
import com.app.api.DataTable;
import com.qis.common.service.CrudService;
import com.qis.common.service.IBaseService;
import com.xinri.dao.role.RolesMapper;
import com.xinri.po.role.Roles;
import com.xinri.service.role.impl.RolesServiceImpl;
import com.xinri.vo.role.RolesVo;

import java.util.Map;

/**
 * <p></p>
 * 类名:RolesService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IRolesService extends IBaseService<Roles> {
    DataTable<RolesVo> findListByVo(DataTable<RolesVo> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);


}

