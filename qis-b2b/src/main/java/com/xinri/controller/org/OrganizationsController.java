package com.xinri.controller.org;

import com.app.api.DataTable;
import com.app.api.JsTree;
import com.app.util.JsonMapper;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.departments.Departments;
import com.xinri.po.organizations.Organizations;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.org.OrgInfoVo;
import com.xinri.vo.org.OrgListVo;
import com.xinri.vo.org.request.OAOrgRequest;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/organization")
public class OrganizationsController extends BaseController {

    @Autowired
    private IOrganizationsService organizationsService;

    @Autowired
    private IDepartmentsService departmentsService;

    @Autowired
    private IUsersService usersService;

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

    /**
     * 保存基础信息
     * @param oaOrgRequest
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(OAOrgRequest oaOrgRequest) {



        if(oaOrgRequest.getSupId().contains("o")){
            Organizations organizations=new Organizations();
            organizations=organizationsService.createOrg(oaOrgRequest,organizations);

            if (oaOrgRequest.getId() != null) {
                organizations.setIsNewRecord(true);
                organizations.setId(null);
            }

            organizationsService.saveOrUpdate(organizations);
        }else if(oaOrgRequest.getSupId().contains("d")){
            Departments departments=new Departments();
            departments=departmentsService.createDeparments(oaOrgRequest,departments);

            if (oaOrgRequest.getId() != null) {
                departments.setIsNewRecord(true);
                departments.setId(null);
            }

            departmentsService.saveOrUpdate(departments);
        }
        ModelAndView mv = new ModelAndView("redirect:/organization/index");
        return mv;
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

    /**
     * 同步OA组织架构
     * @return
     */
    @RequestMapping(value = "sync-oa",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> syncOA(){
        Map<String,Object> map=new HashMap<>();
        try{
            organizationsService.syncOAOrg(new ArrayList<>());
            departmentsService.syncOADept(new ArrayList<>());
            usersService.syncUsers(new ArrayList<>());
            map.put("stat",true);
        }catch (Exception e){
            map.put("stat",false);
            map.put("error",e);
            logger.error("同步OA内容出错:"+e);
        }
        return map;
    }


    @RequestMapping(value = "sealOrg", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> sealOrg(@RequestParam(value = "id") String id){
        Map<String,Object> map=new HashMap<>();
        Long odid = Long.parseLong(id.substring(0,id.length() - 1));
        if(id.contains("o")){
            map=organizationsService.sealOrg(odid,map);
        }else{
            map=departmentsService.sealDept(odid,map);
        }
        return map;
    }



}
