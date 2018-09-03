package com.xinri.controller.module;

import com.qis.common.web.BaseController;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoPermissions;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.*;
import com.xinri.service.permissions.IPermissionsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import com.xinri.vo.jstree.State;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementPermission;
import java.util.*;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */

@Controller("moduleController")
@RequestMapping(value = "module")
public class ModuleController extends BaseController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IRoleModuleInfosService roleModuleInfosService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private IRoleModuleInfoPermissionHeadsService roleModuleInfoPermissionHeadsService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    @Autowired
    private IColumnDatasService columnDatasService;

    @Autowired
    private IRoleModuleInfoColumnDataHeadsService roleModuleInfoColumnDataHeadsService;



    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        logger.info("findModuleList开始");
        ModelAndView mv = new ModelAndView("/module/tree");
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
        map.put("module", moduleInfoesService.get(id));
        map.put("parents", getParents());
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
     * @return
     * @param模块信息和是否有效
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(ModuleInfoes moduleInfo) {
        logger.info("create开始");
        ModelAndView mv = new ModelAndView("redirect:/module/index/");
        if (moduleInfo.getId() != null) {
            moduleInfo.setIsNewRecord(false);
        }
        if (moduleInfo.getParentModuleId() == null) {
            moduleInfo.setParentModuleId(0L);
        } else if (moduleInfo.getId() == null) {
            moduleInfoesService.saveOrUpdate(moduleInfo);
            ModuleInfoPermissions moduleInfoPermission = new ModuleInfoPermissions();
            List<Permissions> permissions = permissionsService.findAllList();
            for (Permissions permission : permissions) {
                moduleInfoPermission.setId(null);
                moduleInfoPermission.setIsNewRecord(true);
                Long moduleInfoId = moduleInfo.getId();
                Long permissionId = permission.getId();
                moduleInfoPermission.setModuleInfoId(moduleInfoId);
                moduleInfoPermission.setPermissionId(permissionId);
                moduleInfoPermission.setIsEffective(0);
                moduleInfoPermissionsService.saveOrUpdate(moduleInfoPermission);
            }
            ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
            List<ColumnDatas> columnDatas = columnDatasService.findAllList();
            for (ColumnDatas columnData : columnDatas) {
                moduleInfoColumnData.setId(null);
                moduleInfoColumnData.setIsNewRecord(true);
                Long moduleInfoId = moduleInfo.getId();
                Long permissionId = columnData.getId();
                moduleInfoColumnData.setModuleInfoId(moduleInfoId);
                moduleInfoColumnData.setColumnDataId(permissionId);
                moduleInfoColumnData.setIsEffective(0);
                moduleInfoColumnDatasService.saveOrUpdate(moduleInfoColumnData);
            }
            return mv;
        }
        moduleInfoesService.saveOrUpdate(moduleInfo);
        logger.info("create结束");
        return mv;
    }

    /**
     * 物理删除
     *
     * @return 状态信息
     * @param要删除的模块的id
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxStatus delete(@PathVariable("id") Long id) {
        logger.info("delete" + id);


        Long[] perIds = moduleInfoPermissionsService.getIdsByModuleId(id);
        if(perIds!=null&&perIds.length>0){
            roleModuleInfoPermissionHeadsService.deleteByRelateId(Arrays.asList(perIds));
        }

        Long[] colIds = moduleInfoColumnDatasService.getIdsByModuleId(id);
        if(colIds!=null&&colIds.length>0){
            roleModuleInfoColumnDataHeadsService.deleteByRelateId(Arrays.asList(colIds));
        }

        ModuleInfoPermissions moduleInfoPermission = new ModuleInfoPermissions();
        moduleInfoPermission.setModuleInfoId(id);
        moduleInfoPermission.setIsEffective(0);
        moduleInfoPermission.setIsNewRecord(false);
        moduleInfoPermissionsService.deleteByModuleId(id);

        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        moduleInfoColumnData.setModuleInfoId(id);
        moduleInfoColumnData.setIsEffective(0);
        moduleInfoColumnData.setIsNewRecord(false);
        moduleInfoColumnDatasService.deleteByModuleId(id);

        return moduleInfoesService.deleteModule(id);
    }

    /**
     * 检查同级下是否存在同名(dicValue)的模块
     *
     * @return
     * @paramdicValue(值)，dicPid(父级编号)，status(是修改还是新增)，id
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
        module.setIsDeleted(0);
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
        module.setIsDeleted(0);
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
        List<Long> ids = moduleInfoPermissionsService.getPermissionIds(moduleId);
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

    @RequestMapping(value = "/getColumnDatas", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getColumnDatas(Long moduleId, Long roleId) {
        logger.info("getColumnDatas开始");
        Map<String, Object> info = new HashMap<String, Object>();
        List<Long> ids = moduleInfoColumnDatasService.getColumnDataIds(moduleId);
        List<ColumnDatas> columnDatas = new ArrayList<>();
        ColumnDatas columnData = new ColumnDatas();
        for (Long pid : ids) {
            columnData = columnDatasService.get(pid);
            columnDatas.add(columnData);
        }
        info.put("columnDatas", columnDatas);
        List<Long> colIds = roleModuleInfoColumnDataHeadsService.getColIds(moduleId, roleId);
        info.put("colIds", colIds);
        logger.info("getColumnDatas结束");
        return info;
    }

    public List<ModuleInfoes> findList(ModuleInfoes moduleInfo,Long id){
        return moduleInfoesService.findList(moduleInfo);
    }

    //初始化模块权限关联表
//    @RequestMapping(value = "/init",method = RequestMethod.GET)
//    public void init (){
//        List<Permissions> permissions=permissionsService.findAllList();
//        ModuleInfoPermissions moduleInfoPermission=new ModuleInfoPermissions();
//        ModuleInfoes moduleinfo=new ModuleInfoes();
//        moduleinfo.setIsMenu(0);
//        List<ModuleInfoes> moduleInfoes = moduleInfoesService.findList(moduleinfo);
//        for (Permissions permission : permissions) {
//            moduleInfoPermission.setPermissionId(permission.getId());
//            for (ModuleInfoes moduleInfo : moduleInfoes) {
//                moduleInfoPermission.setId(null);
//                moduleInfoPermission.setIsNewRecord(true);
//                moduleInfoPermission.setModuleInfoId(moduleInfo.getId());
//                moduleInfoPermissionsService.saveOrUpdate(moduleInfoPermission);
//            }
//        }
//    }
    //初始化模块数据列关联表
//    @RequestMapping(value = "/init",method = RequestMethod.GET)
//    public void init (){
//        List<ColumnDatas> columnDatas=columnDatasService.findAllList();
//        ModuleInfoColumnDatas moduleInfoColumnData=new ModuleInfoColumnDatas();
//        ModuleInfoes moduleinfo=new ModuleInfoes();
//        moduleinfo.setIsMenu(0);
//        List<ModuleInfoes> moduleInfoes = moduleInfoesService.findList(moduleinfo);
//        for (ColumnDatas columnData : columnDatas) {
//            moduleInfoColumnData.setColumnDataId(columnData.getId());
//            for (ModuleInfoes moduleInfo : moduleInfoes) {
//                moduleInfoColumnData.setId(null);
//                moduleInfoColumnData.setIsNewRecord(true);
//                moduleInfoColumnData.setIsEffective(0);
//                moduleInfoColumnData.setModuleInfoId(moduleInfo.getId());
//                moduleInfoColumnDatasService.saveOrUpdate(moduleInfoColumnData);
//            }
//        }
//    }
}
