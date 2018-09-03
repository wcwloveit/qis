package com.xinri.controller.role;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qis.ShiroUser;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.qis.util.Utils;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataHeads;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;
import com.xinri.po.moduleInfo.RoleModuleInfos;
import com.xinri.po.role.RoleClasses;
import com.xinri.po.role.Roles;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.UserGroups;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.role.IRoleClassesService;
import com.xinri.service.role.IRoleUserGroupsService;
import com.xinri.service.role.IRolesService;
import com.xinri.service.user.ISysUserService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.redis.Redis;
import com.xinri.vo.role.RolesVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.*;

@Controller("roleController")
@RequestMapping(value = "/role")
public class RoleController extends BaseController {


    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IRoleModuleInfosService roleModuleInfosService;

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IRoleModuleInfoPermissionHeadsService roleModuleInfoPermissionHeadsService;

    @Autowired
    private IRoleUserGroupsService roleUserGroupsService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    @Autowired
    private IColumnDatasService columnDatasService;

    @Autowired
    private IRoleModuleInfoColumnDataHeadsService roleModuleInfoColumnDataHeadsService;

    @Autowired
    private IRoleClassesService roleClassesService;

    @Autowired
    private ISysUserService sysUserService;


    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findRoleList() {
        return "role/list";
    }


    /*
     * 首页
     * */
    @RequestMapping(value = "module/{id}", method = RequestMethod.GET)
    public ModelAndView findModuleList(@PathVariable(value = "id") Long id) {
        List<Long> moduleIds = roleModuleInfosService.getModuleIds(id);
        if (CollectionUtils.isNotEmpty(moduleIds)) {
            ModelAndView mv = new ModelAndView("role/moduleList");
            mv.addObject("role", rolesService.get(id));
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("role/list");
            mv.addObject("message", "当前角色下没有模块");
            return mv;
        }


    }

    @ResponseBody
    @RequestMapping(value = "moduleList/{id}", method = RequestMethod.POST)
    public DataTable<ModuleInfoes> getModuleList(DataTable<ModuleInfoes> dt, @PathVariable(value = "id") Long id) {
        List<Long> ids = roleModuleInfosService.getModuleIds(id);
        return moduleInfoesService.getModulesForRole(dt, ids);
    }

    /*
     * 分页列表
     * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<RolesVo> getItemList(DataTable<RolesVo> dt, ServletRequest request) {
        logger.info("获取角色列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<RolesVo> baseDatas = rolesService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取角色列表结束");
        return baseDatas;
    }

    /**
     * 跳转新增
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/role/form");
        List<RoleClasses> roleClasses = new ArrayList<RoleClasses>();
        RoleClasses roleClass = new RoleClasses();
        roleClass.setIsDeleted(0);
        roleClasses = roleClassesService.findList(roleClass);
        mv.addObject("roleClasses",roleClasses);
        mv.addObject("action", "create");
        return mv;
    }


    /**
     * 新建
     *
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(Roles role, String ids,
                               RedirectAttributes attributes) {
        logger.info("新增角色开始");
        List<Long> list = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(ids)) {
            for (String str : ids.split(",")) {
                list.add(Long.valueOf(str));
            }
            list = Utils.removeDuplicate(list);
        }

        ModelAndView mv = new ModelAndView("redirect:/role/index");
        try {
            role.setIsDeleted(0);
            role.setIsEffective(0);
            rolesService.saveOrUpdate(role);
            for (Long id : list) {
                RoleModuleInfos roleModuleInfo = new RoleModuleInfos();
                roleModuleInfo.setRoleId(role.getId());
                roleModuleInfo.setModuleInfoId(id);
                roleModuleInfosService.saveOrUpdate(roleModuleInfo);
            }
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "添加角色成功");
            logger.info("新增角色完成");
        } catch (Exception e) {
            logger.error("新增角色报错：", e);
            attributes.addFlashAttribute("message", "添加角色报错");
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
        ModelAndView mv = new ModelAndView("/role/form");
        Roles role = rolesService.get(id);
        List<RoleClasses> roleClasses = new ArrayList<RoleClasses>();
        RoleClasses roleClass = new RoleClasses();
        roleClass.setIsDeleted(0);
        roleClasses = roleClassesService.findList(roleClass);
        mv.addObject("roleClasses",roleClasses);
        mv.addObject("role", role);
        mv.addObject("action", "update");//跳转编辑的标示
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
    public ModelAndView update(Roles role, String ids, RedirectAttributes attributes) {
        logger.info("更新角色开始");
        ModelAndView mv = new ModelAndView("redirect:/role/index");
        List<Long> list = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(ids)) {
            for (String str : ids.split(",")) {
                list.add(Long.valueOf(str));
            }
            list = Utils.removeDuplicate(list);
        }
        RoleModuleInfos roleModuleInfo = new RoleModuleInfos();
        roleModuleInfo.setRoleId(role.getId());
        roleModuleInfosService.removeByEntity(roleModuleInfo);

        try {
            role.setIsNewRecord(false);
            rolesService.saveOrUpdate(role);
            for (Long id : list) {
                roleModuleInfo = new RoleModuleInfos();
                roleModuleInfo.setRoleId(role.getId());
                roleModuleInfo.setModuleInfoId(id);
                roleModuleInfosService.saveOrUpdate(roleModuleInfo);
            }
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "更新角色成功");
            logger.info("更新角色完成");
        } catch (Exception e) {
            logger.error("更新角色报错：", e);
            attributes.addFlashAttribute("message", "更新角色报错");
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
        return rolesService.deleteOne(id);
    }

    /**
     * 全选删除角色信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value = "delete-all", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            for (Long id : ids) {
                rolesService.deleteOne(id);
            }
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.info("删除角色错误信息：" + e);
        }
        return map;
    }

    @RequestMapping(value = "permissionsSave", method = RequestMethod.POST)
    public String permissionsSave(Long roleId, Long moduleId, Long[] ids, RedirectAttributes redirectAttributes) {
        try {
            roleModuleInfoPermissionHeadsService.celar(moduleId, roleId);
            List<Long> perIds=new ArrayList<>();
            if(ids!=null&&ids.length>0){
                 perIds = Arrays.asList(ids);
            }
            Long[] moduPerIds = moduleInfoPermissionsService.getIds(moduleId, perIds);
            RoleModuleInfoPermissionHeads roleModuleInfoPermissionHead = new RoleModuleInfoPermissionHeads();
            roleModuleInfoPermissionHead.setRoleId(roleId);
            for (Long moduPerId : moduPerIds) {
                roleModuleInfoPermissionHead.setId(null);
                roleModuleInfoPermissionHead.setIsNewRecord(true);
                roleModuleInfoPermissionHead.setModuleInfoPermissionId(moduPerId);
                roleModuleInfoPermissionHeadsService.saveOrUpdate(roleModuleInfoPermissionHead);
            }
            logger.info("roleModuleInfoPermissionHead成功");
            redirectAttributes.addFlashAttribute("message", "保存成功");
        } catch (Exception e) {
            logger.error("roleModuleInfoPermissionHead成功", e);
            redirectAttributes.addFlashAttribute("message", "保存失败");
        }
        return "redirect:/role/module/" + roleId;
    }

    @RequestMapping(value = "columnDatasSave", method = RequestMethod.POST)
    public String columnDatasSave(Long roleId, Long moduleId, Long[] colIds, RedirectAttributes redirectAttributes) {
        try {
            roleModuleInfoColumnDataHeadsService.celar(moduleId, roleId);
            List<Long> cIds =new ArrayList<>();
            if(colIds!=null&&colIds.length>0){
                cIds= Arrays.asList(colIds);
            }
            Long[] moduPerIds = moduleInfoColumnDatasService.getIds(moduleId, cIds);
            RoleModuleInfoColumnDataHeads roleModuleInfoColumnDataHead = new RoleModuleInfoColumnDataHeads();
            roleModuleInfoColumnDataHead.setRoleId(roleId);
            for (Long moduPerId : moduPerIds) {
                roleModuleInfoColumnDataHead.setId(null);
                roleModuleInfoColumnDataHead.setIsNewRecord(true);
                roleModuleInfoColumnDataHead.setModuleInfoColumnDataId(moduPerId);
                roleModuleInfoColumnDataHeadsService.saveOrUpdate(roleModuleInfoColumnDataHead);
            }
            logger.info("roleModuleInfoColumnDataHead成功");
            redirectAttributes.addFlashAttribute("message", "保存成功");
        } catch (Exception e) {
            logger.error("roleModuleInfoColumnDataHead成功", e);
            redirectAttributes.addFlashAttribute("message", "保存失败");
        }
        return "redirect:/role/module/" + roleId;
    }

    /**
     * 查询某个角色下的组织
     *
     * @param dt
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping(value = "query-group-list")
    @ResponseBody
    public DataTable queryMessList(DataTable<UserGroups> dt, @RequestParam(value = "roleId", required = false) String roleId, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        return roleUserGroupsService.QueryUserByRoleIdList(dt, searchParams, roleId);
    }

    @RequestMapping(value = "query-group-notinrole")
    @ResponseBody
    public DataTable queryNotInRoleList(DataTable<UserGroups> dt, @RequestParam(value = "roleId", required = false) String roleId, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        return roleUserGroupsService.QueryGroupNotInRoleIdList(dt, searchParams, roleId);
    }

    /**
     * 员工加入角色
     *
     * @param roleId
     * @param empId
     * @return
     */
    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ResponseBody
    public AjaxStatus join(@RequestParam("roleId") Long roleId, @RequestParam("groupId") Long groupId) {
        return roleUserGroupsService.JoinRole(roleId, groupId);
    }

    /**
     * 员工离开角色
     *
     * @param roleId
     * @param empId
     * @return
     */
    @RequestMapping(value = "leave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxStatus leave(@RequestParam("roleId") Long roleId, @RequestParam("groupId") Long groupId) {
        return roleUserGroupsService.LeaveRole(roleId, groupId);
    }

    public Redis getInfo(ShiroUser user){
        Redis info=new Redis();
        if (user.type==1){
            SysUser sysUser=sysUserService.get(user.id);
            Roles role=rolesService.get(Long.valueOf(sysUser.getRoleid()));
            RoleModuleInfos before=new RoleModuleInfos();
            before.setRoleId(role.getId());
            List<RoleModuleInfos> roleModuleInfos= roleModuleInfosService.findList(before);
            List<ModuleInfoes> moduleInfoes=new ArrayList<>();
            for (RoleModuleInfos roleModuleInfo : roleModuleInfos) {
                ModuleInfoes moduleInfo=moduleInfoesService.get(roleModuleInfo.getModuleInfoId());
                moduleInfoes.add(moduleInfo);
            }
            List<Roles> roles=new ArrayList<>();
            roles.add(role);
            info.setModuleInfoesList(moduleInfoes);
            info.setRoles(roles);
        }else if(user.type==2){
            info.setModuleInfoesList(moduleInfoesService.findAllList());
        }
        return info;
    }

}
