package com.xinri.service.organizations.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.organizations.Organizations;
import com.xinri.dao.organizations.OrganizationsMapper;
import com.xinri.service.organizations.IOrganizationsService;
/**
 * <p></p>
 * 类名:OrganizationsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("organizationsService")
public class OrganizationsServiceImpl extends CrudService<OrganizationsMapper,Organizations>  implements IOrganizationsService{


}