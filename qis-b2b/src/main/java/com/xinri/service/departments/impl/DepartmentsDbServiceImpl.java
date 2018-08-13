package com.xinri.service.departments.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.departments.DepartmentsDb;
import com.xinri.dao.departments.DepartmentsDbMapper;
import com.xinri.service.departments.IDepartmentsDbService;
/**
 * <p></p>
 * 类名:DepartmentsDbServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("departmentsDbService")
public class DepartmentsDbServiceImpl extends CrudService<DepartmentsDbMapper,DepartmentsDb>  implements IDepartmentsDbService{


}