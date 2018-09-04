package com.xinri.controller.module;

import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.service.moduleInfo.IColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.moduleInfo.IRoleModuleInfoColumnDataHeadsService;
import com.xinri.service.moduleInfo.impl.ModuleInfoesServiceImpl;
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

@Controller
@RequestMapping(value = "module/moduleColumnDatas")
public class ModuleColumnDatasController {

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IColumnDatasService ColumnDatasService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    @Autowired
    private IRoleModuleInfoColumnDataHeadsService roleModuleInfoColumnDataHeadsService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView findModuleList() {
        ModelAndView mv = new ModelAndView("/module/col");
        ColumnDatas columnData = new ColumnDatas();
        columnData.setIsDeleted(0);
        mv.addObject("columnDatas", ColumnDatasService.findList(columnData));//返回所有的权限信息供页面选择
        return mv;
    }

    @RequestMapping(value = "getCols/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getCols(@PathVariable Long id) {
        Map map = new HashMap();
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        moduleInfoColumnData.setModuleInfoId(id);
        moduleInfoColumnData.setIsEffective(1);
        map.put("module", moduleInfoesService.get(id));
        map.put("myCols", moduleInfoColumnDatasService.findList(moduleInfoColumnData));
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(Long id, String[] cols) {
        ModuleInfoColumnDatas moduleInfoColumnDatas = new ModuleInfoColumnDatas();
        moduleInfoColumnDatas.setModuleInfoId(id);
        moduleInfoColumnDatas.setIsEffective(0);
        moduleInfoColumnDatasService.relate(moduleInfoColumnDatas);

        Long[] ids = (Long[]) ConvertUtils.convert(cols,Long.class);
        if(ids!=null&&ids.length>0){
        roleModuleInfoColumnDataHeadsService.deleteByDiff(Arrays.asList(ids),id);
        }
        if (cols != null) {
            for (String col : cols) {
                moduleInfoColumnDatas.setModuleInfoId(id);
                moduleInfoColumnDatas.setColumnDataId(Long.valueOf(col));
                moduleInfoColumnDatas.setIsEffective(1);
                moduleInfoColumnDatasService.relate(moduleInfoColumnDatas);
            }
        }
        ModelAndView mv = new ModelAndView("redirect:/module/moduleColumnDatas/index/");
        return mv;
    }
}
