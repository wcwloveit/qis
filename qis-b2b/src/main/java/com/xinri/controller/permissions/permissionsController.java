package com.xinri.controller.permissions;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.permissions.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "permissions")
public class permissionsController extends BaseController {

    @Autowired
    private IPermissionsService permissionsService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findTypesList() {
        return "permissions/list";
    }

    /*
     * 分页列表
     * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<Permissions> getItemList(DataTable<Permissions> dt, ServletRequest request){
        logger.info("获取权限列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<Permissions> baseDatas = permissionsService.findList(dt, searchParams); //查询方法
        logger.info("获取权限列表结束");
        return baseDatas;
    }

    @ResponseBody
    @RequestMapping(value = "permissions",method = RequestMethod.GET)
    public List<Permissions> getPermissions(){
        return permissionsService.findAllList();
    }

    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/permissions/form");
        mv.addObject("action","create");
        return mv;
    }

    /**
     * 新建
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(Permissions permissions,
                               RedirectAttributes attributes) {
        logger.info("新增权限开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/index");
        try {
            permissions.setIsDeleted(0);
            permissions.setIsEffective(0);
            permissionsService.saveOrUpdate(permissions);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加权限成功");
            logger.info("新增权限完成");
        } catch (Exception e) {
            logger.error("新增权限报错：", e);
            attributes.addFlashAttribute("message", "添加权限报错");
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
        ModelAndView mv = new ModelAndView("/permissions/form");
        Permissions permissions = permissionsService.get(id);
        mv.addObject("permissions",permissions);
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
    public ModelAndView update(Permissions permissions, RedirectAttributes attributes) {
        logger.info("更新权限开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/index");
        try {
            permissions.setIsNewRecord(false);
            permissionsService.saveOrUpdate(permissions);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新权限成功");
            logger.info("更新权限完成");
        } catch (Exception e) {
            logger.error("更新权限报错：", e);
            attributes.addFlashAttribute("message", "更新权限报错");
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
        return  permissionsService.deleteOne(id);
    }

    /**
     * 全选删除权限信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value="delete-all",method=RequestMethod.POST) @ResponseBody
    public Map<String,Boolean> deleteAll(@RequestParam("ids") List<Long> ids){
        Map<String,Boolean> map=new HashMap<String,Boolean>();
        try{
            for (Long id:ids){
                permissionsService.deleteOne(id);
            }
            map.put("stat",true);
        }catch(Exception e){
            map.put("stat",false);
            logger.info("删除权限错误信息："+e);
        }
        return map;
    }
}
