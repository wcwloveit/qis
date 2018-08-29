package com.xinri.controller.permissions;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
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

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Controller
@RequestMapping(value = "permissions")
public class permissionsController extends BaseController {

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

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
    public DataTable<Permissions> getItemList(DataTable<Permissions> dt, ServletRequest request) {
        logger.info("获取权限列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<Permissions> baseDatas = permissionsService.findList(dt, searchParams); //查询方法
        logger.info("获取权限列表结束");
        return baseDatas;
    }

    @ResponseBody
    @RequestMapping(value = "permissions", method = RequestMethod.GET)
    public List<Permissions> getPermissions() {
        logger.info("获取所有权限");
        return permissionsService.findAllList();
    }

    /**
     * 跳转新增
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        logger.info("创建权限开始");
        ModelAndView mv = new ModelAndView("/permissions/form");
        mv.addObject("action", "create");
        logger.info("创建权限结束");
        return mv;
    }

    /**
     * 新建
     *
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(Permissions permissions,
                               RedirectAttributes attributes) {
        logger.info("新增权限开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/index");
        ModuleInfoPermissions moduleInfoPermission=new ModuleInfoPermissions();
        List<ModuleInfoes> moduleInfoes = moduleInfoesService.findAllList();
        try {
            permissions.setIsDeleted(0);
            permissions.setIsEffective(0);
            permissionsService.saveOrUpdate(permissions);
            for (ModuleInfoes moduleInfo : moduleInfoes) {
                moduleInfoPermission.setId(null);
                moduleInfoPermission.setIsNewRecord(true);
                Long moduleInfoId=moduleInfo.getId();
                Long permissionId=permissions.getId();
                moduleInfoPermission.setModuleInfoId(moduleInfoId);
                moduleInfoPermission.setPermissionId(permissionId);
                moduleInfoPermissionsService.saveOrUpdate(moduleInfoPermission);
            }
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "添加权限成功");
            logger.info("新增权限完成");
        } catch (Exception e) {
            logger.error("新增权限报错：", e);
            attributes.addFlashAttribute("message", "添加权限报错");
        }
        return mv;
    }


    /**
     * 更新状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        logger.info("跳转更新权限页面开始");
        ModelAndView mv = new ModelAndView("/permissions/form");
        Permissions permissions = permissionsService.get(id);
        mv.addObject("permissions", permissions);
        mv.addObject("action", "update");//跳转编辑的标示
        logger.info("跳转更新权限页面结束");
        return mv;
    }

    /**
     * 更新
     *
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
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "更新权限成功");
            logger.info("更新权限完成");
        } catch (Exception e) {
            logger.error("更新权限报错：", e);
            attributes.addFlashAttribute("message", "更新权限报错");
        }
        return mv;
    }

    /**
     * 根据Id逻辑删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteOne-{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        logger.info("删除权限"+id);
        return permissionsService.deleteOne(id);
    }

    /**
     * 全选删除权限信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value = "delete-all", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        logger.info("删除所有权限开始");
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            for (Long id : ids) {
                permissionsService.deleteOne(id);
            }
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除权限错误信息：" + e);
        }
        return map;
    }
}
