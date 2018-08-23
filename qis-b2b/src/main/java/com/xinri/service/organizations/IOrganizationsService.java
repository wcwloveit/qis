package com.xinri.service.organizations;
import com.app.api.DataTable;
import com.app.api.JsTree;
import com.qis.common.service.IBaseService;
import com.xinri.po.organizations.Organizations;
import com.xinri.vo.org.OAOrgVo;
import com.xinri.vo.org.OrgInfoVo;
import com.xinri.vo.org.OrgListVo;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:OrganizationsService<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */

public interface IOrganizationsService extends IBaseService<Organizations>{

    public void syncOAOrg(List<OAOrgVo> orgList);

    public List<JsTree> getOrgTree(String supId);

    public OrgInfoVo read(String id);

    public DataTable<OrgListVo> getOrgList(Map<String, Object> searchParams, String id, DataTable<OrgListVo> dt);
}

