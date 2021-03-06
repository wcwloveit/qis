package com.xinri.controller.role;

import com.app.api.DataTable;
import com.app.util.StatusMsgUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qis.ShiroUser;
import com.qis.common.mapper.JsonMapper;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.qis.util.Utils;
import com.xinri.po.departments.Departments;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataHeads;
import com.xinri.po.moduleInfo.RoleModuleInfoPermissionHeads;
import com.xinri.po.moduleInfo.RoleModuleInfos;
import com.xinri.po.role.RoleClasses;
import com.xinri.po.role.Roles;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.UserGroups;
import com.xinri.po.user.Users;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.role.IRoleClassesService;
import com.xinri.service.role.IRoleUserGroupsService;
import com.xinri.service.role.IRolesService;
import com.xinri.service.user.ISysUserService;
import com.xinri.service.user.IUsersService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.moduleInfo.RoleModuleInFoPerVo;
import com.xinri.vo.moduleInfo.RoleModuleInfoCVo;
import com.xinri.vo.moduleInfo.RoleModuleInfoColVo;
import com.xinri.vo.moduleInfo.RoleModuleInfoPCVo;
import com.xinri.vo.redis.Redis;
import com.xinri.vo.role.RolesVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色
 * 创建人 汪震 20180907
 */
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
    private IRoleModuleInfoPermissionLinesService roleModuleInfoPermissionLinesService;

    @Autowired
    private IRoleModuleInfoColumnDataLinesService roleModuleInfoColumnDataLinesService;

    @Autowired
    private IRoleClassesService roleClassesService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IDepartmentsService departmentsService;

    /**
     * 首页
     * @return
     * 创建人 汪震 20180907
     */

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findRoleList() {
        return "role/list";
    }


    /**
     * 角色下的模块管理页
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
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

    /**
     * 返回角色下的模块列表
     * @param dt
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    @ResponseBody
    @RequestMapping(value = "moduleList/{id}", method = RequestMethod.POST)
    public DataTable<ModuleInfoes> getModuleList(DataTable<ModuleInfoes> dt, @PathVariable(value = "id") Long id) {
        List<Long> ids = roleModuleInfosService.getModuleIds(id);
        return moduleInfoesService.getModulesForRole(dt, ids);
    }

    /**
     * 分页列表
     * @param dt
     * @param request
     * @return
     * 创建人 汪震 20180907
     */

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
     * 跳转新增页面
     * @return
     * 创建人 汪震 20180907
     */

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/role/form");
        List<RoleClasses> roleClasses = new ArrayList<RoleClasses>();
        RoleClasses roleClass = new RoleClasses();
        roleClass.setIsDeleted(0);
        roleClasses = roleClassesService.findList(roleClass);
        mv.addObject("roleClasses", roleClasses);
        mv.addObject("action", "create");
        return mv;
    }


    /**
     * 新增角色
     * @param role
     * @param ids
     * @param attributes
     * @return
     * 创建人 汪震 20180907
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
                roleModuleInfo.setId(null);
                roleModuleInfo.setIsNewRecord(true);
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
     * 跳转更新页面
     * @param id
     * @return
     * 创建人 汪震 20180907
     */

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("/role/form");
        Roles role = rolesService.get(id);
        List<RoleClasses> roleClasses = new ArrayList<RoleClasses>();
        RoleClasses roleClass = new RoleClasses();
        roleClass.setIsDeleted(0);
        roleClasses = roleClassesService.findList(roleClass);
        mv.addObject("roleClasses", roleClasses);
        mv.addObject("role", role);
        mv.addObject("action", "update");//跳转编辑的标示
        return mv;
    }

    /**
     * 更新
     * @param role
     * @param ids
     * @param attributes
     * @return
     * 创建人 汪震 20180907
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
                roleModuleInfo.setId(null);
                roleModuleInfo.setIsNewRecord(true);
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
     * 根据id逻辑删除
     * @param id
     * @return
     * 创建人 汪震 20180907
     */

    @RequestMapping(value = "deleteOne/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        return rolesService.deleteOne(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * 创建人 汪震 20180907
     */

    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
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

    /**
     * 查询某个角色下的组织列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "query-group-list")
    @ResponseBody
    public DataTable queryMessList(DataTable<UserGroups> dt, @RequestParam(value = "roleId", required = false) String roleId, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        return roleUserGroupsService.QueryUserByRoleIdList(dt, searchParams, roleId);
    }

    /**
     * 查询某个角色下不存在的组织列表
     * @param dt
     * @param roleId
     * @param request
     * @return
     * 创建人 汪震 20180907
     */
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
     * @param groupId
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
     * @param groupId
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "leave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxStatus leave(@RequestParam("roleId") Long roleId, @RequestParam("groupId") Long groupId) {
        return roleUserGroupsService.LeaveRole(roleId, groupId);
    }

    /**
     * 根据角色id和模块id获取角色模块权限
     * @param moduleId
     * @param roleId
     * @return
     * 创建者 夏善勇 20180904
     */
    @RequestMapping(value = "/getModulePermissions-{moduleId}-{roleId}", method = RequestMethod.GET)
    public ModelAndView getModulePermissions(@PathVariable("moduleId") Long moduleId,
                                             @PathVariable("roleId") Long roleId,
                                             RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        logger.info("getRoleModulePermissions开始");
        List<RoleModuleInFoPerVo> voList  = new  ArrayList<RoleModuleInFoPerVo>();
        RoleModuleInFoPerVo vo = new RoleModuleInFoPerVo();
        vo.setModuleId(moduleId);
        vo.setRoleId(roleId);
        voList = moduleInfoPermissionsService.getRoleModuleInFoPerVo(vo);

        if(voList==null){
            mv.setViewName("redirect:/role/module/" + roleId);
            redirectAttributes.addFlashAttribute("message", "当前无模块");
        }else{
            mv.setViewName("/permissions/roleModuleList");

            ModuleInfoes info =  moduleInfoesService.get(moduleId);
            Roles role = rolesService.get(roleId);
            mv.addObject("moList",voList);
            mv.addObject("info",info);
            mv.addObject("role",role);
        }


//        mv.addObject("message","moList");
//        mv.addObject("success",true);
        logger.info("getRoleModulePermissions结束");
        return  mv;
    }


    /**
     * 根据角色id和模块id获取角色模块数据列
     * @param moduleId
     * @param roleId
     * @return
     * 创建者 夏善勇 20180906
     */
    @RequestMapping(value = "/getModuleColumnData-{moduleId}-{roleId}", method = RequestMethod.GET)
    public ModelAndView getModuleColumnData(@PathVariable("moduleId") Long moduleId,
                                            @PathVariable("roleId") Long roleId ,
                                            RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("/columnDatas/roleModuleList");
        logger.info("getModuleColumnData开始");
        List<RoleModuleInfoColVo> voList  = new  ArrayList<RoleModuleInfoColVo>();
        RoleModuleInfoColVo vo = new RoleModuleInfoColVo();
        vo.setModuleId(moduleId);
        vo.setRoleId(roleId);
        voList = moduleInfoColumnDatasService.getRoleModuleInFoColumnVo(vo);

        if(voList==null){
            redirectAttributes.addFlashAttribute("message", "当前无数据列");
            mv.setViewName("redirect:/role/module/" + roleId);
        }else {
            mv.setViewName("/columnDatas/roleModuleList");
            ModuleInfoes info =  moduleInfoesService.get(moduleId);
            Roles role = rolesService.get(roleId);

            mv.addObject("coList",voList);
            mv.addObject("info",info);
            mv.addObject("role",role);
        }

//        mv.addObject("message","moList");
//        mv.addObject("success",true);
        logger.info("getModuleColumnData结束");
        return  mv;
    }

    /**
     * 保存角色模块权限
     * @param body
     * @param request
     * @param response
     * @return
     * 创建者 夏善勇 20180906
     * 修改者 汪震 20180907
     */
    @RequestMapping(value="/modulePermissionsSave",method= RequestMethod.POST)
    public String list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        logger.info("保存角色模块权限开始");
        String code = StatusMsgUtils.SUCCEEDEDCODE_200;
        String msg = StatusMsgUtils.ResponseCode.getName(code);
        try {
            RoleModuleInfoPCVo vo = (RoleModuleInfoPCVo) JsonMapper.fromJsonString(body,RoleModuleInfoPCVo.class);
            List<Long> beforeIds = roleModuleInfoPermissionHeadsService.getBeforeIds(vo.getRoleId(), vo.getModuleId());
            List<Long> afterModulePermissionIds=vo.getmList().stream().filter(RoleModuleInFoPerVo -> RoleModuleInFoPerVo.getrIsEffective() == 1).map(RoleModuleInFoPerVo->RoleModuleInFoPerVo.getModulePermissionId()).collect(Collectors.toList());
            List<Long> afterIds=null;
            if(CollectionUtils.isNotEmpty(afterModulePermissionIds)){
                afterIds = roleModuleInfoPermissionHeadsService.getAfterIds(vo.getRoleId(), afterModulePermissionIds);
            }else{
                afterIds=new ArrayList<>();
            }

            beforeIds.removeAll(afterIds);
            if (CollectionUtils.isNotEmpty(beforeIds)){
                List<Long> idsByDiff = roleModuleInfoPermissionLinesService.getIdsByDiff(beforeIds);
                if(CollectionUtils.isNotEmpty(idsByDiff)){
                    throw new Exception("存在关联，无法删除");
                }
            }
            if(vo.getmList()!=null){
                for(RoleModuleInFoPerVo perVo: vo.getmList()){
                    RoleModuleInfoPermissionHeads heads = new RoleModuleInfoPermissionHeads();
                    if(perVo.getRmphId()!=null){//更新
                        heads.setIsNewRecord(false);
                        heads.setId(perVo.getRmphId());
                        heads.setIsEffective(perVo.getrIsEffective());
                    }else if(perVo.getrIsEffective()==1){//新建
                        heads.setIsEffective(1);
                        heads.setRoleId(vo.getRoleId());
                        heads.setModuleInfoPermissionId(perVo.getModulePermissionId());
                    }
                    roleModuleInfoPermissionHeadsService.saveOrUpdate(heads);
                }
            }
        } catch (Exception e) {
            logger.error("保存角色模块权限报错：", e);
            code = StatusMsgUtils.ERRORCODE_400;
            msg = StatusMsgUtils.ResponseCode.getName(code);
        }
        logger.info("保存角色模块权限完成");
        return responseJsonData(code, msg, null,response);
    }



    /**
     * 保存角色模块数据列
     * @param vo
     * @param request
     * @param response
     * @return
     * 创建者 汪震 20180907
     */
    @RequestMapping(value="/moduleColumnsSave",method= RequestMethod.POST)
    @ResponseBody
    public AjaxStatus list(@RequestBody RoleModuleInfoCVo vo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("保存角色模块开始");
        String code = StatusMsgUtils.SUCCEEDEDCODE_200;
        String msg = StatusMsgUtils.ResponseCode.getName(code);
        try {
            List<Long> beforeIds = roleModuleInfoColumnDataHeadsService.getBeforeIds(vo.getRoleId(), vo.getModuleId());
            List<Long> afterModuleColumnDataIds=vo.getmList().stream().filter(RoleModuleInfoColVo -> RoleModuleInfoColVo.getrIsEffective() == 1).map(RoleModuleInfoColVo->RoleModuleInfoColVo.getModuleColumnId()).collect(Collectors.toList());
            List<Long> afterIds=null;
            if(CollectionUtils.isNotEmpty(afterModuleColumnDataIds)){
                afterIds = roleModuleInfoColumnDataHeadsService.getAfterIds(vo.getRoleId(), afterModuleColumnDataIds);
            }else{
                afterIds=new ArrayList<>();
            }
            beforeIds.removeAll(afterIds);
            if (CollectionUtils.isNotEmpty(beforeIds)){
                List<Long> idsByDiff = roleModuleInfoColumnDataLinesService.getIdsByDiff(beforeIds);
                if(CollectionUtils.isNotEmpty(idsByDiff)){
                    throw new Exception("存在关联，无法删除");
                }
            }
            if(vo.getmList()!=null){
                for(RoleModuleInfoColVo colVo: vo.getmList()){
                    RoleModuleInfoColumnDataHeads heads = new RoleModuleInfoColumnDataHeads();
                    if(colVo.getRmchId()!=null){//更新
                        heads.setIsNewRecord(false);
                        heads.setId(colVo.getRmchId());
                        heads.setIsEffective(colVo.getrIsEffective());
                        roleModuleInfoColumnDataHeadsService.saveOrUpdate(heads);
                    }else if(colVo.getrIsEffective()==1){//新建
                        heads.setIsEffective(1);
                        heads.setRoleId(vo.getRoleId());
                        heads.setModuleInfoColumnDataId(colVo.getModuleColumnId());
                        roleModuleInfoColumnDataHeadsService.saveOrUpdate(heads);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("保存角色模块权限报错：", e);
            code = StatusMsgUtils.ERRORCODE_400;
            msg = StatusMsgUtils.ResponseCode.getName(code);
        }
        logger.info("保存角色模块权限完成");
        AjaxStatus ajaxStatus=new AjaxStatus(code,msg,response);
        return ajaxStatus;
    }

//    /**
//     *
//     * @param roleId
//     * @param moduleId
//     * @param ids
//     * @param redirectAttributes
//     * @return
//     */
//    @RequestMapping(value = "permissionsSave", method = RequestMethod.POST)
//    public String permissionsSave(Long roleId, Long moduleId, Long[] ids, RedirectAttributes redirectAttributes) {
//        try {
//            roleModuleInfoPermissionHeadsService.celar(moduleId, roleId);
//            List<Long> perIds = new ArrayList<>();
//            if (ids != null && ids.length > 0) {
//                perIds = Arrays.asList(ids);
//            }
//            Long[] moduPerIds = moduleInfoPermissionsService.getIds(moduleId, perIds);
//            RoleModuleInfoPermissionHeads roleModuleInfoPermissionHead = new RoleModuleInfoPermissionHeads();
//            roleModuleInfoPermissionHead.setRoleId(roleId);
//            for (Long moduPerId : moduPerIds) {
//                roleModuleInfoPermissionHead.setId(null);
//                roleModuleInfoPermissionHead.setIsNewRecord(true);
//                roleModuleInfoPermissionHead.setModuleInfoPermissionId(moduPerId);
//                roleModuleInfoPermissionHeadsService.saveOrUpdate(roleModuleInfoPermissionHead);
//            }
//            logger.info("roleModuleInfoPermissionHead成功");
//            redirectAttributes.addFlashAttribute("message", "保存成功");
//        } catch (Exception e) {
//            logger.error("roleModuleInfoPermissionHead成功", e);
//            redirectAttributes.addFlashAttribute("message", "保存失败");
//        }
//        return "redirect:/role/module/" + roleId;
//    }
//
//    @RequestMapping(value = "columnDatasSave", method = RequestMethod.POST)
//    public String columnDatasSave(Long roleId, Long moduleId, Long[] colIds, RedirectAttributes redirectAttributes) {
//        try {
//            roleModuleInfoColumnDataHeadsService.celar(moduleId, roleId);
//            List<Long> cIds = new ArrayList<>();
//            if (colIds != null && colIds.length > 0) {
//                cIds = Arrays.asList(colIds);
//            }
//            Long[] moduPerIds = moduleInfoColumnDatasService.getIds(moduleId, cIds);
//            RoleModuleInfoColumnDataHeads roleModuleInfoColumnDataHead = new RoleModuleInfoColumnDataHeads();
//            roleModuleInfoColumnDataHead.setRoleId(roleId);
//            for (Long moduPerId : moduPerIds) {
//                roleModuleInfoColumnDataHead.setId(null);
//                roleModuleInfoColumnDataHead.setIsNewRecord(true);
//                roleModuleInfoColumnDataHead.setModuleInfoColumnDataId(moduPerId);
//                roleModuleInfoColumnDataHeadsService.saveOrUpdate(roleModuleInfoColumnDataHead);
//            }
//            logger.info("roleModuleInfoColumnDataHead成功");
//            redirectAttributes.addFlashAttribute("message", "保存成功");
//        } catch (Exception e) {
//            logger.error("roleModuleInfoColumnDataHead成功", e);
//            redirectAttributes.addFlashAttribute("message", "保存失败");
//        }
//        return "redirect:/role/module/" + roleId;
//    }

}
