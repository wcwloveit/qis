package com.xinri.controller.users;

import com.app.api.DataTable;
import com.app.api.JsTree;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.sun.org.apache.regexp.internal.RE;
import com.xinri.po.user.Users;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.org.OrgListVo;
import com.xinri.vo.users.OAUsersVo;
import com.xinri.vo.users.UserGroupsVo;
import com.xinri.vo.users.UserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UsersController extends BaseController{

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IOrganizationsService organizationsService;

    @Autowired
    private IDepartmentsService departmentsService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String findUsers(Model model) {
        return "user/userList";
    }


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public DataTable<UserListVo> findUsersList(DataTable<UserListVo> dt, ServletRequest request){
        Map<String,Object> searchParams= Servlets.getParametersStartingWith(request,"search_");
        return  usersService.getUserList(searchParams,"",dt);
    }



    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        return "user/userForm";
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String create(@PathVariable("id") Long id,Model model) {
        model.addAttribute("action", "update");

        OAUsersVo oaUsersVo=new OAUsersVo();
        Users users=new Users();
        users=usersService.get(id);

        oaUsersVo.setDeptid(users.getDepartmentId());
        try{
            oaUsersVo.setDepname(departmentsService.get(users.getDepartmentId()).getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        oaUsersVo.setLastname(users.getName());
        oaUsersVo.setMobile(users.getMobilePhone());
        oaUsersVo.setWorkcode(users.getUserNo());
        oaUsersVo.setUsername(users.getUserName());
        oaUsersVo.setStatus(users.getIsEffective().toString());

        model.addAttribute("u", oaUsersVo);

        return "user/userForm";
    }




    /**
     * 根据组织ID获取所有在此组织级别下的员工信息
     *
     * @param dt
     * @param id
     *
     * @return
     */
    @RequestMapping(value="user-list/{id}",method=RequestMethod.POST)
    @ResponseBody
    public DataTable<UserListVo> ListEmpInOrg(DataTable<UserListVo> dt, @PathVariable String id, ServletRequest request){
        Map<String,Object> searchParams= Servlets.getParametersStartingWith(request,"search_");
        return  usersService.getUserList(searchParams,id,dt);
    }


    /**
     * 人员封存
     * @param id
     * @return
     */
    @RequestMapping(value = "sealUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> sealUser(@RequestParam(value = "id") String id,@RequestParam(value = "type") String type){
        Map<String,Object> map=new HashMap<>();
        try{
            Users users=new Users();
            users.setId(Long.parseLong(id));
            if(type.equals("1")){
                users.setIsEffective(1);
            }else{
                users.setIsEffective(0);
            }
            usersService.updateByCondition(users);
            map.put("stat",true);
        }catch (Exception e){
            map.put("stat",false);
            if(type.equals("1")){
                map.put("msg","人员封存失败！");
            }else{
                map.put("msg","人员解封失败！");
            }

            logger.error("人员封存错误："+e);
        }
        return map;
    }





}
