package com.xinri.controller.user;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.departments.Departments;
import com.xinri.po.role.RoleClasses;
import com.xinri.po.user.UserGroups;
import com.xinri.po.user.UserUserGroups;
import com.xinri.po.user.Users;
import com.xinri.service.user.IUserGroupsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.role.RoleClassesVo;
import com.xinri.vo.users.UserGroupsVo;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/userGroup")
public class UserGroupsController extends BaseController {

    @Autowired
    private IUserGroupsService userGroupsService;

    /*
   * 首页
   * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList() {
        return "userGroup/list";
    }

    /*
  * 分页列表
  * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<UserGroupsVo> getItemList(DataTable<UserGroupsVo> dt, ServletRequest request) {
        logger.info("获取产品列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<UserGroupsVo> userGroups = userGroupsService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取产品列表结束始");
        return userGroups;
    }

    /**
     * 跳转新增
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/userGroup/form");
        mv.addObject("action", "create");
        return mv;
    }

    /**
     * 新增
     *
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(UserGroups userGroups,
                             RedirectAttributes attributes) {
        logger.info("新增产品开始");
        ModelAndView mv = new ModelAndView("redirect:/userGroup/index"); //重定向
        try {
            userGroupsService.saveOrUpdate(userGroups);
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "添加产品成功");
            logger.info("新增产品完成");
        } catch (Exception e) {
            logger.error("新增产品报错：", e);
            attributes.addFlashAttribute("message", "添加产品报错");
        }
        return mv;
    }

    /**
     * 更新状态
     *
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        UserGroups userGroups = new UserGroups();
        userGroups = userGroupsService.get(id);
        model.addAttribute("action", "update");//跳转编辑的标示
        model.addAttribute("roleClasses", userGroups);//商品信息
        return "/userGroup/form"; //跳转到更新页面
    }


    /*
* 更新
* */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateitemDetail(UserGroups userGroups, RedirectAttributes attributes) {
        logger.info("更新产品开始");
        ModelAndView mv = new ModelAndView("redirect:/userGroup/index");
        try {
            userGroups.setIsNewRecord(false);
            userGroupsService.saveOrUpdate(userGroups);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新产品成功");
            logger.info("更新产品完成");
        } catch (Exception e) {
            logger.error("更新产品报错：", e);
            attributes.addFlashAttribute("message", "更新产品报错");
        }
        return mv;
    }

//    /**
//     * 逻辑删除
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = {"delete-{id}"}, method = {RequestMethod.POST})
//    @ResponseBody
//    public JSONObject LogicDel(@PathVariable Long id){
//        JSONObject json = new JSONObject();
//        try{
//            //逻辑删除商品信息
//            UserGroups userGroups = new UserGroups();
//            userGroups.setId(id); //自己的是 编号
//            userGroups.setIsDeleted(1);//把id为XX的哪一个实体类数据的IsDeleted 改为1
//            userGroupsService.saveOrUpdate(userGroups);//跟新实体类
//            json.put("code","200");
//            json.put("msg","删除成功");
//        }catch(Exception e){
//            e.printStackTrace();
//            json.put("code","8001");
//            json.put("msg","操作失败，请联系管理员！");
//        }
//        return json;
//    }


//    /**
//     * 批量删除
//     * @return 返回跳转链接
//     */
//    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
//        Map<String, Boolean> map = new HashMap();
//        try {
//            userGroupsService.batchDelete(ids);//batchDelete逻辑删除
//            map.put("stat", true);
//        } catch (Exception e) {
//            map.put("stat", false);
//            logger.error("删除错误信息：{}", e);
//        }
//        return map;
//    }


    /**
     * 判断用户组是否关联项
     */
     @RequestMapping(value="check/{id}") @ResponseBody
    public boolean check(@PathVariable("id") Long id){
        boolean isDel=userGroupsService.CheckUserInRole(id);
        return isDel;
    }

    /**
     * 删除角色组
     * */
    @RequestMapping(value="delete/{id}") @ResponseBody
    public AjaxStatus delete(@PathVariable("id") Long id){
        userGroupsService.DeleteKnRole(id);
        return new AjaxStatus(true,"删除角色成功");
    }


    /**
     * 用户组分配人员列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping(value="query-user-notinrole")
    @ResponseBody
    public DataTable queryNotInRoleList(DataTable<Users> dt, @RequestParam(value="roleId", required=false) Long roleId, ServletRequest request){
        Map<String,Object> searchParams=Servlets.getParametersStartingWith(request,"search_");
        return userGroupsService.QueryUserNotInRidList(dt,searchParams,roleId);
    }

    /**
     * 离开人员列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping(value="query-user-list")
    @ResponseBody
    public DataTable queryMessList(DataTable<Users> dt, @RequestParam(value="roleId", required=false) Long roleId, ServletRequest request){
        Map<String,Object> searchParams=Servlets.getParametersStartingWith(request,"search_");
        return userGroupsService.QueryUserByRidList(dt,searchParams,roleId);
    }

    /**
     * 人员加入角色组
     *
     * @param roleId
     * @param empId
     *
     * @return
     */
    @RequestMapping(value="join", method=RequestMethod.POST) @ResponseBody
    public AjaxStatus join(@RequestParam("roleId") Long roleId, @RequestParam("empId") Long empId){
        return userGroupsService.JoinRole(roleId,empId);
    }

    /**
     * 人员离开人员组
     *
     * @param roleId
     * @param empId
     *
     * @return
     */
    @RequestMapping(value="leave", method=RequestMethod.POST) @ResponseBody
    public AjaxStatus leave(@RequestParam("roleId") Long roleId,@RequestParam("empId") Long empId){
        return userGroupsService.LeaveRole(roleId,empId);
    }


    /**
     * 用户组分配部门列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping(value="query-departments-notinrole")
    @ResponseBody
    public DataTable queryNotInRoleList2(DataTable<Departments> dt, @RequestParam(value="roleId", required=false) Long roleId, ServletRequest request){
        Map<String,Object> searchParams=Servlets.getParametersStartingWith(request,"search_");
        return userGroupsService.QueryUserNotInRidList2(dt,searchParams,roleId);
    }

    /**
     * 部门加入角色组
     *
     * @param roleId
     * @param empId
     *
     * @return
     */
    @RequestMapping(value="join2", method=RequestMethod.POST) @ResponseBody
    public AjaxStatus join2(@RequestParam("roleId") Long roleId, @RequestParam("empId") Long empId){
        return userGroupsService.JoinRole2(roleId,empId);
    }

    /**
     * 离开人员列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping(value="query-departments-list")
    @ResponseBody
    public DataTable queryMessList2(DataTable<Departments> dt, @RequestParam(value="roleId", required=false) Long roleId, ServletRequest request){
        Map<String,Object> searchParams=Servlets.getParametersStartingWith(request,"search_");
        return userGroupsService.QueryUserByRidList2(dt,searchParams,roleId);
    }

    /**
     * 部门离开人员组
     *
     * @param roleId
     * @param empId
     *
     * @return
     */
    @RequestMapping(value="leave2", method=RequestMethod.POST) @ResponseBody
    public AjaxStatus leave2(@RequestParam("roleId") Long roleId,@RequestParam("empId") Long empId){
        return userGroupsService.LeaveRole2(roleId,empId);
    }
}
