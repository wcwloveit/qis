package com.xinri.service.organizations;
import com.qis.common.service.IBaseService;
import com.xinri.po.organizations.Organizations;
import com.xinri.vo.org.OAOrgVo;

import java.util.List;

/**
 * <p></p>
 * 类名:OrganizationsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IOrganizationsService extends IBaseService<Organizations>{

    public void syncOAOrg(List<OAOrgVo> orgList);
}

