package com.xinri.controller.dept;

import com.app.api.DataTable;
import com.app.api.JsTree;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.vo.org.OrgInfoVo;
import com.xinri.vo.org.OrgListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/department")
public class DeptmentsController extends BaseController{

    @Autowired
    private IDepartmentsService departmentsService;


    /**
     * 根据组织ID获取所有在此组织级别下的员工信息
     *
     * @param dt
     * @param id
     *
     * @return
     */
    @RequestMapping(value="dept-list/{id}",method=RequestMethod.POST)
    @ResponseBody
    public DataTable<OrgListVo> ListEmpInOrg(DataTable<OrgListVo> dt, @PathVariable String id, ServletRequest request){
        Map<String,Object> searchParams= Servlets.getParametersStartingWith(request,"search_");
        return  departmentsService.getDeptList(searchParams,id,dt);
    }


}
