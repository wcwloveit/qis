package com.xinri.controller.module;

import com.app.api.DataTable;
import com.app.util.StatusMsgUtils;
import com.qis.common.mapper.JsonMapper;
import com.qis.common.web.BaseController;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionLines;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.moduleInfo.impl.ModuleInfoesServiceImpl;
import com.xinri.service.permissions.IPermissionsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.moduleInfo.RoleModuleInFoPermissionLineVo;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 模块-权限
 * 创建人 汪震 20180907
 */
@Controller
@RequestMapping(value = "module/modulePermissions")
public class ModulePermissionsController extends BaseController{

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

    @Autowired
    private IRoleModuleInfoPermissionHeadsService roleModuleInfoPermissionHeadsService;
    @Autowired
    private IRoleModuleInfoPermissionLinesService roleModuleInfoPermissionLinesService;

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

    /**创建者 夏善勇  创建时间 2018.9.7
     * 获取当前角色id，模块id下的 权限列数据
     * @param dt
     * @param roleId
     * @param infoId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "lines-{roleId}-{infoId}", method = RequestMethod.POST)
    public DataTable<RoleModuleInFoPermissionLineVo> getModuleList(DataTable<RoleModuleInFoPermissionLineVo> dt,
                                                                   @PathVariable(value = "roleId") Long roleId,
                                                                   @PathVariable(value = "infoId") Long infoId) {
        return roleModuleInfoPermissionLinesService.getModulesForRole(dt, roleId,infoId);
    }


    /**创建者 夏善勇  创建时间 2018.9.10
     * 逻辑删除 角色权限line
     * @param id
     * @return
     */
    @RequestMapping(value="roleDelete/{id}")
    public String roleDelete(@PathVariable("id") Long id, HttpServletResponse response){
        roleModuleInfoPermissionLinesService.deleteById(id);
        return responseJsonData("200", "离开成功", null,response);
    }

    /**创建者 夏善勇  创建时间 2018.9.10
     * 保存权限line
     * @param body
     * @param response
     * @return
     */
    @RequestMapping(value="roleSave")
    public String delete(@RequestBody String body, HttpServletResponse response){
        logger.info("保存角色模块权限line开始");
        String code = StatusMsgUtils.SUCCEEDEDCODE_200;
        String msg = StatusMsgUtils.ResponseCode.getName(code);
        try {
            RoleModuleInfoPermissionLines vo = (RoleModuleInfoPermissionLines) JsonMapper.fromJsonString(body,RoleModuleInfoPermissionLines.class);
            RoleModuleInfoPermissionLines lines = new RoleModuleInfoPermissionLines();
            lines = roleModuleInfoPermissionLinesService.get(vo);
            if(lines==null){
                vo.setIsEffective(1);
                roleModuleInfoPermissionLinesService.saveOrUpdate(vo);
            }else{
                if(lines.getIsDeleted()==0){
                    code = StatusMsgUtils.ERRORCODE_8002;
                    msg = StatusMsgUtils.ResponseCode.getName(code);
                }else{
                    lines.setIsDeleted(0);
                    lines.setIsNewRecord(false);
                    roleModuleInfoPermissionLinesService.saveOrUpdate(lines);
                }
            }
        } catch (Exception e) {
            logger.error("保存角色模块权限line报错：", e);
            code = StatusMsgUtils.ERRORCODE_400;
            msg = StatusMsgUtils.ResponseCode.getName(code);
        }
        logger.info("保存角色模块权限line完成");
        return responseJsonData(code, msg, null,response);
    }
}
