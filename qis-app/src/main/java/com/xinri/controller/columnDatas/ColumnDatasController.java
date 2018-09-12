package com.xinri.controller.ColumnDatas;

import com.app.api.DataTable;
import com.app.util.StatusMsgUtils;
import com.qis.common.mapper.JsonMapper;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoes;

import com.xinri.po.moduleInfo.RoleModuleInfoColumnDataLines;
import com.xinri.service.moduleInfo.*;

import com.xinri.vo.columnData.ColumnDataVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Controller
@RequestMapping(value = "permissions/columnDatas")
public class ColumnDatasController extends BaseController {

    @Autowired
    private IColumnDatasService ColumnDatasService;

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;

    @Autowired
    private IRoleModuleInfoColumnDataHeadsService moduleInfoColumnDataHeadsService;


    /**
     * 首页
     * @return
     * 创建人 汪震 20180907
     */
    @RequiresPermissions("shiro-column-index")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findTypesList() {
        return "columnDatas/list";
    }

    /**
     * 分页列表
     * @param dt
     * @param request
     * @return
     * 创建人汪震 20180907
     */
    @RequiresPermissions("shiro-column-list")
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<ColumnDataVo> getItemList(DataTable<ColumnDataVo> dt, ServletRequest request) {
        logger.info("获取数据列列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<ColumnDataVo> baseDatas = ColumnDatasService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取数据列列表结束");
        return baseDatas;
    }


    /**
     * 获取所有数据列
     * @return
     * 创建人 汪震 20180907
     */
    @ResponseBody
    @RequestMapping(value = "ColumnDatas", method = RequestMethod.GET)
    public List<ColumnDatas> getColumnDatas() {
        logger.info("获取所有数据列");
        return ColumnDatasService.findAllList();
    }

    /**
     * 跳转新增数据列页面
     * @return
     * 创建人 汪震 20180907
     */
    @RequiresPermissions("shiro-column-create")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        logger.info("创建数据列开始");
        ModelAndView mv = new ModelAndView("/columnDatas/form");
        mv.addObject("action", "create");
        logger.info("创建数据列结束");
        return mv;
    }

    /**
     * 新建数据列
     * @param columnDatas
     * @param attributes
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(ColumnDatas columnDatas,
                               RedirectAttributes attributes) {
        logger.info("新增数据列开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/columnDatas/index");
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        ModuleInfoes moduleinfo = new ModuleInfoes();
        moduleinfo.setIsMenu(0);
        List<ModuleInfoes> moduleInfoes = moduleInfoesService.findList(moduleinfo);
        try {
            columnDatas.setIsEffective(0);
            ColumnDatasService.saveOrUpdate(columnDatas);

            for (ModuleInfoes moduleInfo : moduleInfoes) {
                moduleInfoColumnData.setId(null);
                moduleInfoColumnData.setIsNewRecord(true);
                moduleInfoColumnData.setIsEffective(0);
                Long moduleInfoId = moduleInfo.getId();
                Long columnDataId = columnDatas.getId();
                moduleInfoColumnData.setModuleInfoId(moduleInfoId);
                moduleInfoColumnData.setColumnDataId(columnDataId);
                moduleInfoColumnDatasService.saveOrUpdate(moduleInfoColumnData);
            }

            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "添加数据列成功");
            logger.info("新增数据列完成");
        } catch (Exception e) {
            logger.error("新增数据列报错：", e);
            attributes.addFlashAttribute("message", "添加数据列报错");
        }
        return mv;
    }


    /**
     * 获取更新数据列页面
     * @param id
     * @return
     */
    @RequiresPermissions("shiro-column-edit")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        logger.info("跳转更新数据列页面开始");
        ModelAndView mv = new ModelAndView("/columnDatas/form");
        ColumnDatas ColumnDatas = ColumnDatasService.get(id);
        mv.addObject("ColumnDatas", ColumnDatas);
        mv.addObject("action", "update");//跳转编辑的标示
        logger.info("跳转更新数据列页面结束");
        return mv;
    }

    /**
     * 更新数据列
     * @param ColumnDatas
     * @param attributes
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(ColumnDatas ColumnDatas, RedirectAttributes attributes) {
        logger.info("更新数据列开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/columnDatas/index");
        try {
            ColumnDatas.setIsNewRecord(false);
            ColumnDatasService.saveOrUpdate(ColumnDatas);
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "更新数据列成功");
            logger.info("更新数据列完成");
        } catch (Exception e) {
            logger.error("更新数据列报错：", e);
            attributes.addFlashAttribute("message", "更新数据列报错");
        }
        return mv;
    }

    /**
     * 根据id逻辑删除数据列 及物理删除与数据列关联的 模块-数据列及角色-模块-数据列 信息
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    @RequiresPermissions("shiro-column-delete")
    @RequestMapping(value = "deleteOne/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        logger.info("删除数据列" + id);

        Long[] ids = moduleInfoColumnDatasService.getIdsByColumnDataId(id);
        if(ids!=null&&ids.length>0){
            moduleInfoColumnDataHeadsService.deleteByRelateId(Arrays.asList(ids));
        }
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        moduleInfoColumnData.setColumnDataId(id);
        moduleInfoColumnData.setIsEffective(0);
        moduleInfoColumnData.setIsNewRecord(false);
        moduleInfoColumnDatasService.deleteByColumnDataId(id);

        return ColumnDatasService.deleteOne(id);
    }

    /**
     * 删除多行数据列
     * @param ids
     * @return
     * 创建人 汪震 20180907
     */
    @RequiresPermissions("shiro-column-deleteAll")
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        logger.info("删除所有数据列开始");
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            for (Long id : ids) {
                ColumnDatasService.deleteOne(id);
            }
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除数据列错误信息：" + e);
        }
        return map;
    }


}
