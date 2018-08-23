package com.xinri.controller.module;

import com.qis.common.web.BaseController;
import com.xinri.po.module.ModuleInfoes;
import com.xinri.po.permissions.Permissions;
import com.xinri.po.permissionsToModule.ModuleInfoPermissions;
import com.xinri.service.module.IModuleInfoesService;
import com.xinri.service.permissions.IPermissionsService;
import com.xinri.service.permissionsToModule.IPermissionsToModuleService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "module")
public class ModuleController extends BaseController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IPermissionsToModuleService permissionsToModuleService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/tree");
        Permissions permission=new Permissions();
        permission.setIsDeleted(0);
        mv.addObject("permissions", permissionsService.findList(permission));//返回所有的权限信息供页面选择
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<JsTree> list() {
        return moduleInfoesService.getTree();
    }

    /**
     * 返回jsTree点选项的详细信息和上级菜单及权限信息
     *
     * @param id 资源ID
     */
    @RequestMapping(value = "get-infos/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getInfos(@PathVariable Long id) {
        Map map = new HashMap();
        ModuleInfoPermissions moduleInfoPermission = new ModuleInfoPermissions();
        moduleInfoPermission.setModuleInfoId(id);
        map.put("module", moduleInfoesService.get(id));
        map.put("myPers", permissionsToModuleService.findList(moduleInfoPermission));
        map.put("parents", getParents());
        return map;
    }

    /**
     * 返回类型为类的模块
     *
     * @return List<Dictionary>
     */
    @RequestMapping(value = "parentsList", method = RequestMethod.POST)
    @ResponseBody
    public List<ModuleInfoes> getParents() {
        ModuleInfoes moduleInfo = new ModuleInfoes();
        moduleInfo.setParentModuleId(0L);
        return moduleInfoesService.findList(moduleInfo);
    }

    /**
     * 新建或修改
     *
     * @param 模块信息和是否有效
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(ModuleInfoes moduleinfo, String[] pers) {
        if (moduleinfo.getId() != null) {
            moduleinfo.setIsNewRecord(false);
        }
        if (moduleinfo.getParentModuleId() == null) {
            moduleinfo.setParentModuleId(0L);
        }
        moduleInfoesService.saveOrUpdate(moduleinfo);
        ModuleInfoPermissions moduleInfoPermissions = new ModuleInfoPermissions();
        moduleInfoPermissions.setModuleInfoId(moduleinfo.getId());
        permissionsToModuleService.removeByEntity(moduleInfoPermissions);
        if (pers != null && moduleinfo.getParentModuleId() != 0) {
            for (String per : pers) {
                moduleInfoPermissions.setIsNewRecord(true);
                moduleInfoPermissions.setId(null);
                moduleInfoPermissions.setPermissionId(Long.valueOf(per));
                permissionsToModuleService.saveOrUpdate(moduleInfoPermissions);
            }
        }
        ModelAndView mv = new ModelAndView("redirect:/module/index/");
        return mv;
    }

    /**
     * 物理删除
     *
     * @param 要删除的模块的id
     * @return 状态信息
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxStatus delete(@PathVariable("id") Long id) {
//        ModuleInfoPermissions moduleInfoPermissions = new ModuleInfoPermissions();
//        moduleInfoPermissions.setModuleInfoId(id);
//        permissionsToModuleService.removeByEntity(moduleInfoPermissions);
        return moduleInfoesService.deleteModule(id);
    }

    /**
     * 检查同级下是否存在同名(dicValue)的模块
     *
     * @param dicValue(值)，dicPid(父级编号)，status(是修改还是新增)，id
     * @return
     */
    @RequestMapping(value = "/checkExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean Check(String name, Long pid, String status, Long id) {
        if (id != 0 && "update".equals(status) && name.equals(moduleInfoesService.get(id).getName())) {
            return true; //如果是修改且改之前和改之后的name一样的话则直接返回true
        }
        ModuleInfoes module = new ModuleInfoes();
        module.setParentModuleId(pid);
        module.setName(name);
        List<ModuleInfoes> modules = moduleInfoesService.findList(module);
        if (CollectionUtils.isNotEmpty(modules)) {
            return false;   //同级下存在同名(name)的模块，返回false
        }
        return true;
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkCode(String code, String status, Long id) {
        if (id != 0 && "update".equals(status) && code.equals(moduleInfoesService.get(id).getCode())) {
            return true; //如果是修改且改之前和改之后的code一样的话则直接返回true
        }
        ModuleInfoes module = new ModuleInfoes();
        module.setCode(code);
        List<ModuleInfoes> modules = moduleInfoesService.findList(module);
        if (CollectionUtils.isNotEmpty(modules)) {
            return false;   //同级下存在同名(ocde)的模块，返回false
        }
        return true;
    }
}
