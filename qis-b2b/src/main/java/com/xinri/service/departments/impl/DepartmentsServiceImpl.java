package com.xinri.service.departments.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.departments.Departments;
import com.xinri.dao.departments.DepartmentsMapper;
import com.xinri.service.departments.IDepartmentsService;
/**
 * <p></p>
 * 类名:DepartmentsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("departmentsService")
public class DepartmentsServiceImpl extends CrudService<DepartmentsMapper,Departments>  implements IDepartmentsService{


}