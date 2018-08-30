package com.xinri.controller.module;

import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.moduleInfo.IPermissionsToModuleService;
import com.xinri.service.moduleInfo.impl.ModuleInfoesServiceImpl;
import com.xinri.service.permissions.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "module/modulePermissions")
public class ModulePermissionsController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/per");
        Permissions permission = new Permissions();
        permission.setIsDeleted(0);
        mv.addObject("permissions", permissionsService.findList(permission));//返回所有的权限信息供页面选择
        return mv;
    }

    @RequestMapping(value = "getPers/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getPers(@PathVariable Long id) {
        Map map = new HashMap();
        ModuleInfoPermissions moduleInfoPermission = new ModuleInfoPermissions();
        moduleInfoPermission.setModuleInfoId(id);
        moduleInfoPermission.setIsEffective(1);
        map.put("module", moduleInfoesService.get(id));
        map.put("myPers", moduleInfoPermissionsService.findList(moduleInfoPermission));
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(Long id, String[] pers) {
        ModuleInfoPermissions moduleInfoPermissions = new ModuleInfoPermissions();
        moduleInfoPermissions.setModuleInfoId(id);
        moduleInfoPermissions.setIsEffective(0);
        moduleInfoPermissionsService.relate(moduleInfoPermissions);
        if (pers != null) {
            for (String per : pers) {
                moduleInfoPermissions.setModuleInfoId(id);
                moduleInfoPermissions.setPermissionId(Long.valueOf(per));
                moduleInfoPermissions.setIsEffective(1);
                moduleInfoPermissionsService.relate(moduleInfoPermissions);
            }
        }
        ModelAndView mv = new ModelAndView("redirect:/module/modulePermissions/index/");
        return mv;
    }
}
