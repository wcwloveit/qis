package com.xinri.controller.module;

import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.moduleInfo.IPermissionsToModuleService;
import com.xinri.service.moduleInfo.IRoleModuleInfoPermissionHeadsService;
import com.xinri.service.moduleInfo.impl.ModuleInfoesServiceImpl;
import com.xinri.service.permissions.IPermissionsService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 模块-权限
 * 创建人 汪震 20180907
 */
@Controller
@RequestMapping(value = "module/modulePermissions")
public class ModulePermissionsController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

    @Autowired
    private IRoleModuleInfoPermissionHeadsService roleModuleInfoPermissionHeadsService;

    /**
     * 首页
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/per");
        Permissions permission = new Permissions();
        permission.setIsDeleted(0);
        mv.addObject("permissions", permissionsService.findList(permission));//返回所有的权限信息供页面选择
        return mv;
    }

    /**
     * 获取此模块拥有的所有权限信息
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
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

    /**
     * 保存
     * @param id
     * @param pers
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(Long id, String[] pers) {
        ModuleInfoPermissions moduleInfoPermissions = new ModuleInfoPermissions();
        moduleInfoPermissions.setModuleInfoId(id);
        moduleInfoPermissions.setIsEffective(0);
        moduleInfoPermissionsService.relate(moduleInfoPermissions);

        Long[] ids = (Long[]) ConvertUtils.convert(pers,Long.class);
        if(ids!=null&&ids.length>0){
        roleModuleInfoPermissionHeadsService.deleteByDiff(Arrays.asList(ids),id);
        }
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
