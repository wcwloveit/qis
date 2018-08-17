package com.xinri.service.departments.impl;
import com.xinri.po.organizations.Organizations;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.service.port.IPortService;
import com.xinri.vo.dept.OADepartmentVo;
import com.xinri.vo.org.OAOrgVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.departments.Departments;
import com.xinri.dao.departments.DepartmentsMapper;
import com.xinri.service.departments.IDepartmentsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * 类名:DepartmentsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("departmentsService")
public class DepartmentsServiceImpl extends CrudService<DepartmentsMapper,Departments>  implements IDepartmentsService{

    @Autowired
    private IPortService portService;

    @Autowired
    private IOrganizationsService organizationsService;

    /**
     * 同步OA组织
     * @param deptList
     */
    @Override
    @Transactional(readOnly = false)
    public void syncOADept(List<OADepartmentVo> deptList) {
        if (deptList.isEmpty()) {
            String deptInfo = portService.sendPort("OADept", "", new JSONObject());
            deptList = com.alibaba.fastjson.JSONObject.parseArray(deptInfo, OADepartmentVo.class);
        }
        List<OADepartmentVo> surDept = new ArrayList<>();
        List<Departments> departments = new ArrayList<>();
        try {
            for (OADepartmentVo vo : deptList) {

                Departments dep = new Departments();
                dep.setOaId(vo.getId() + "");

                Departments selectDept = new Departments();
                selectDept = dao.getByEntity(dep);
                if (selectDept != null) {//存在 更新
                    if (!selectDept.getName().equals(vo.getDepartmentname())) {

                    }
                } else {//不存在，插入

                    dep.setName(vo.getDepartmentname());
                    if(vo.getCanceled()==null){
                        dep.setOaCanceled("-1");
                    }else{
                        dep.setOaCanceled(vo.getCanceled());
                    }

                    dep.setDescr(vo.getDepartmentmark());
                    dep.setDescFlexField1(vo.getParentDeptId() + "");
                    dep.setOaNo(vo.getDepartmentcode());
                    dep.setIsDeleted(0);
                    dep.setCode(vo.getId() + "");
                    dep.setCreatedOn(new Date());
                    dep.setIsEffective(0);
                    Organizations sqlOrg = new Organizations();

                    sqlOrg.setOaId(vo.getCompanyId() + "");
                    Organizations companyId = organizationsService.get(sqlOrg);//获取分部ID

                    dep.setOrganizationId(companyId.getId());
                    if (vo.getParentDeptId() == 0) {//OA最外层
                        dep.setDepthLevel(0); //第一层
                        dep.setParentDepartmentId(0L);
                        departments.add(dep);
                    } else {
                        Departments parentSql = new Departments();
                        parentSql.setOaId(vo.getParentDeptId() + "");
                        Departments parent = new Departments();
                        parent = dao.getByEntity(parentSql);
                        if (parent != null) {//父节点存在
                            dep.setDepthLevel(parent.getDepthLevel() + 1);
                            dep.setParentDepartmentId(parent.getId());
                            departments.add(dep);
                        } else {
                            surDept.add(vo);
                        }
                    }
                }
            }
            if (!departments.isEmpty()) {
                dao.insertDeptList(departments);
            }
            if (!surDept.isEmpty()) {//全部完成，进行插入
                syncOADept(surDept);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("组织插入错误：" + e);
        }
    }

}