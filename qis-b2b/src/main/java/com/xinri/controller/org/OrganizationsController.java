package com.xinri.controller.org;

import com.app.api.DataTable;
import com.app.api.JsTree;
import com.app.util.JsonMapper;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.vo.org.OrgInfoVo;
import com.xinri.vo.org.OrgListVo;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/organization")
public class OrganizationsController extends BaseController {

    @Autowired
    private IOrganizationsService organizationsService;

    @RequestMapping(value = "index" ,method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("orgType","o");
//        model.addAttribute("orgTree",JsonMapper.nonEmptyMapper().toJson(os.OrganizationJsTree()));
        return "org/OrganizationsTree";
    }

    @RequestMapping(value = "list", method= RequestMethod.POST, produces="application/json")
    @ResponseBody
    public List<JsTree> jstree(@RequestParam(value="supId",required=false)String supId){
        List<JsTree> jsTreeList=new ArrayList<>();
        jsTreeList=organizationsService.getOrgTree(supId);
        return jsTreeList;
    }

    @RequestMapping(value = "read/{id}", method= RequestMethod.GET)
    @ResponseBody
    public OrgInfoVo getInfo(@PathVariable String id){
        return organizationsService.read(id);
    }


    /**
     * 根据组织ID获取所有在此组织级别下的员工信息
     *
     * @param dt
     * @param id
     *
     * @return
     */
    @RequestMapping(value="org-list/{id}",method=RequestMethod.POST)
    @ResponseBody
    public DataTable<OrgListVo> ListEmpInOrg(DataTable<OrgListVo> dt,@PathVariable String id,ServletRequest request){
        logger.info("开始");
        Map<String,Object> searchParams= Servlets.getParametersStartingWith(request,"search_");
        return  organizationsService.getOrgList(searchParams,id,dt);
    }



}
