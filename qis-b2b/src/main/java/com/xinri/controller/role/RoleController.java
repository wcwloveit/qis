package com.xinri.controller.role;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.role.Roles;
import com.xinri.service.role.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {


    @Autowired
    private IRolesService rolesService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findRoleList(){
        return "role/list";
    }

    /*
     * 分页列表
     * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<Roles> getItemList(DataTable<Roles> dt, ServletRequest request){
        logger.info("获取角色列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<Roles> baseDatas = rolesService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取角色列表结束");
        return baseDatas;
    }

    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/role/form");
        List<Roles> roleList = new ArrayList<Roles>();
        Roles roles = new Roles();
        roles.setIsDeleted(0);
        roleList= rolesService.findAllList(roles);
        mv.addObject("roleList",roleList);
        mv.addObject("action","create");
        return mv;
    }


    /**
     * 新建
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(Roles role,
                               RedirectAttributes attributes) {
        logger.info("新增角色开始");
        ModelAndView mv = new ModelAndView("redirect:/role/index");
        try {
            role.setIsDeleted(0);
            role.setIsEffective(0);
            rolesService.saveOrUpdate(role);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加角色成功");
            logger.info("新增角色完成");
        } catch (Exception e) {
            logger.error("新增角色报错：", e);
            attributes.addFlashAttribute("message", "添加角色报错");
        }
        return mv;
    }


    /**
     * 更新状态
     * @param id
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("/role/form");
        Roles role = rolesService.get(id);
        mv.addObject("role",role);
        mv.addObject("action", "update");//跳转编辑的标示
        return mv;
    }

    /**
     * 更新
     * @param zcActivity
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Roles role, RedirectAttributes attributes) {
        logger.info("更新角色开始");
        ModelAndView mv = new ModelAndView("redirect:/role/index");
        try {
            role.setIsNewRecord(false);
            rolesService.saveOrUpdate(role);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新角色成功");
            logger.info("更新角色完成");
        } catch (Exception e) {
            logger.error("更新角色报错：", e);
            attributes.addFlashAttribute("message", "更新角色报错");
        }
        return mv;
    }

    /**
     * 根据Id逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteOne-{id}",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        return  rolesService.deleteOne(id);
    }

    /**
     * 全选删除角色信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value="delete-all",method=RequestMethod.POST) @ResponseBody
    public Map<String,Boolean> deleteAll(@RequestParam("ids") List<Long> ids){
        Map<String,Boolean> map=new HashMap<String,Boolean>();
        try{
            for (Long id:ids){
                rolesService.deleteOne(id);
            }
            map.put("stat",true);
        }catch(Exception e){
            map.put("stat",false);
            logger.info("删除角色错误信息："+e);
        }
        return map;
    }
}
