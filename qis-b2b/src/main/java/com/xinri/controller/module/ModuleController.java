package com.xinri.controller.module;

import com.qis.common.web.BaseController;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.permissions.Permissions;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.permissions.IPermissionsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import com.xinri.vo.jstree.State;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

@Controller
@RequestMapping(value = "module")
public class ModuleController extends BaseController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IPermissionsToModuleService permissionsToModuleService;

    @Autowired
    private IRoleModuleInfosService roleModuleInfosService;

    @Autowired
    private IRoleModuleInfoPermissionHeadsService roleModuleInfoPermissionHeadsService;

    @Autowired
    private IColumnDatasService columnDatasService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        logger.info("findModuleList开始");
        ModelAndView mv = new ModelAndView("/module/tree");
        Permissions permission = new Permissions();
        permission.setIsDeleted(0);
        mv.addObject("permissions", permissionsService.findList(permission));//返回所有的权限信息供页面选择
        ColumnDatas columnData = new ColumnDatas();
        columnData.setIsDeleted(0);
        mv.addObject("columnDatas", columnDatasService.findList (columnData));
        logger.info("findModuleList结束");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<JsTree> list() {
        logger.info("获取树");
        return moduleInfoesService.getTree();
    }


    @ResponseBody
    @RequestMapping(value = "listForRole/{id}", method = RequestMethod.POST)
    public List<JsTree> listForRole(@PathVariable(value = "id") Long id) {
        logger.info("listForRole开始");
        List<JsTree> jsTrees = moduleInfoesService.getTree();
        List<Long> ids = roleModuleInfosService.getModuleIds(id);

        State state = new State();
        state.setSelected(true);
        for (JsTree jsTree : jsTrees) {
            if (ids.contains(jsTree.getId()) && jsTree.getParent() != "#") {
                jsTree.setState(state);
            }
        }
        logger.info("listForRole结束");
        return jsTrees;
    }

    /**
     * 返回jsTree点选项的详细信息和上级菜单及权限信息
     *
     * @param id 资源ID
     */
    @RequestMapping(value = "get-infos/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getInfos(@PathVariable Long id) {
        logger.info("getInfos开始");
        Map map = new HashMap();
        ModuleInfoPermissions moduleInfoPermission = new ModuleInfoPermissions();
        moduleInfoPermission.setModuleInfoId(id);
        map.put("module", moduleInfoesService.get(id));
        map.put("myPers", permissionsToModuleService.findList(moduleInfoPermission));

        map.put("parents", getParents());
        ModuleInfoColumnDatas moduleInfoColumnData=new ModuleInfoColumnDatas();
        moduleInfoColumnData.setModuleInfoId(id);
        map.put("mycolumns",moduleInfoColumnDatasService.findList(moduleInfoColumnData));
        logger.info("getInfos结束");
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
        logger.info("getParents开始");
        ModuleInfoes moduleInfo = new ModuleInfoes();
        moduleInfo.setParentModuleId(0L);
        logger.info("getParents结束");
        return moduleInfoesService.findList(moduleInfo);
    }

    /**
     * 新建或修改
     *
     * @param模块信息和是否有效
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(ModuleInfoes moduleinfo, String[] pers) {
        logger.info("create开始");
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
        logger.info("create结束");
        return mv;
    }

    /**
     * 物理删除
     *
     * @param要删除的模块的id
     * @return 状态信息
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxStatus delete(@PathVariable("id") Long id) {
        logger.info("delete"+id);
//        ModuleInfoPermissions moduleInfoPermissions = new ModuleInfoPermissions();
//        moduleInfoPermissions.setModuleInfoId(id);
//        permissionsToModuleService.removeByEntity(moduleInfoPermissions);
        return moduleInfoesService.deleteModule(id);
    }

    /**
     * 检查同级下是否存在同名(dicValue)的模块
     *
     * @paramdicValue(值)，dicPid(父级编号)，status(是修改还是新增)，id
     * @return
     */
    @RequestMapping(value = "/checkExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean Check(String name, Long pid, String status, Long id) {
        logger.info("check开始");
        if (id != 0 && "update".equals(status) && name.equals(moduleInfoesService.get(id).getName())) {
            return true; //如果是修改且改之前和改之后的name一样的话则直接返回true
        }
        ModuleInfoes module = new ModuleInfoes();
        module.setParentModuleId(pid);
        module.setName(name);
        List<ModuleInfoes> modules = moduleInfoesService.findList(module);
        logger.info("check结束");
        if (CollectionUtils.isNotEmpty(modules)) {
            return false;   //同级下存在同名(name)的模块，返回false
        }

        return true;
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkCode(String code, String status, Long id) {
        logger.info("checkCode开始");
        if (id != 0 && "update".equals(status) && code.equals(moduleInfoesService.get(id).getCode())) {
            return true; //如果是修改且改之前和改之后的code一样的话则直接返回true
        }
        ModuleInfoes module = new ModuleInfoes();
        module.setCode(code);
        List<ModuleInfoes> modules = moduleInfoesService.findList(module);
        logger.info("checkCode结束");
        if (CollectionUtils.isNotEmpty(modules)) {
            return false;   //同级下存在同名(ocde)的模块，返回false
        }
        return true;
    }

    @RequestMapping(value = "/getPermissions", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPermissions(Long moduleId, Long roleId) {
        logger.info("getPermissions开始");
        Map<String, Object> info = new HashMap<String, Object>();
        List<Long> ids = permissionsToModuleService.getPermissionIds(moduleId);
        List<Permissions> permissions = new ArrayList<>();
        Permissions permission = new Permissions();
        for (Long pid : ids) {
            permission = permissionsService.get(pid);
            permissions.add(permission);
        }
        info.put("permissions", permissions);
        List<Long> perIds = roleModuleInfoPermissionHeadsService.getPerIds(moduleId, roleId);
        info.put("perIds", perIds);
        logger.info("getPermissions结束");
        return info;
    }
}
