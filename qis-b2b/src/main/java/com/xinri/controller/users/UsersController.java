package com.xinri.controller.users;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.org.OrgListVo;
import com.xinri.vo.users.UserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UsersController extends BaseController{

    @Autowired
    private IUsersService usersService;


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


}
