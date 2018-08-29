package com.xinri.controller.module;

import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.permissions.Permissions;
import com.xinri.service.moduleInfo.IColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.permissions.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "moduleColumnDatas")
public class ModuleColumnDatasController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IColumnDatasService ColumnDatasService;

    @Autowired
    private IModuleInfoColumnDatasService ModuleInfoColumnDatasService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/col");
        ColumnDatas permission = new ColumnDatas();
        permission.setIsDeleted(0);
        mv.addObject("columnDatas", ColumnDatasService.findList(permission));//返回所有的权限信息供页面选择
        return mv;
    }

    @RequestMapping(value = "getCols/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getCols(@PathVariable Long id) {
        Map map = new HashMap();
        ModuleInfoColumnDatas moduleInfoPermission = new ModuleInfoColumnDatas();
        moduleInfoPermission.setModuleInfoId(id);
        map.put("module", moduleInfoesService.get(id));
        map.put("myCols", ModuleInfoColumnDatasService.findList(moduleInfoPermission));
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Long id,String[] cols) {
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        moduleInfoColumnData.setModuleInfoId(id);
        ModuleInfoColumnDatasService.removeByEntity(moduleInfoColumnData);
        if (cols != null) {
            for (String col : cols) {
                moduleInfoColumnData.setIsNewRecord(true);
                moduleInfoColumnData.setId(null);
                moduleInfoColumnData.setColumnDataId(Long.valueOf(col));
                ModuleInfoColumnDatasService.saveOrUpdate(moduleInfoColumnData);
            }
        }
        ModelAndView mv = new ModelAndView("redirect:/moduleColumnDatas/index/");
        return mv;
    }
}