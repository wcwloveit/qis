package com.xinri.dao.organizations;
import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.organizations.Organizations;

import java.util.List;

/**
 * 类名:OrganizationsMapper<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
 @MyBatisDao
public interface OrganizationsMapper extends CrudDao<Organizations>{

   public void insertOrgList(List<Organizations> organizations);

   public List<Organizations> initOrgList(Organizations organizations);

}

