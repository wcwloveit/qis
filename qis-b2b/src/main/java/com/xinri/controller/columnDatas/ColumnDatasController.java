package com.xinri.controller.ColumnDatas;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.moduleInfo.ColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.po.moduleInfo.ModuleInfoes;

import com.xinri.service.moduleInfo.IColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.moduleInfo.IRoleModuleInfoColumnDataHeadsService;

import com.xinri.vo.columnData.ColumnDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
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

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findTypesList() {
        return "columnDatas/list";
    }

    /*
     * 分页列表
     * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<ColumnDataVo> getItemList(DataTable<ColumnDataVo> dt, ServletRequest request) {
        logger.info("获取权限列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<ColumnDataVo> baseDatas = ColumnDatasService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取权限列表结束");
        return baseDatas;
    }

    @ResponseBody
    @RequestMapping(value = "ColumnDatas", method = RequestMethod.GET)
    public List<ColumnDatas> getColumnDatas() {
        logger.info("获取所有权限");
        return ColumnDatasService.findAllList();
    }

    /**
     * 跳转新增
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        logger.info("创建权限开始");
        ModelAndView mv = new ModelAndView("/columnDatas/form");
        mv.addObject("action", "create");
        logger.info("创建权限结束");
        return mv;
    }

    /**
     * 新建
     *
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(ColumnDatas columnDatas,
                               RedirectAttributes attributes) {
        logger.info("新增权限开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/columnDatas/index");
        ModuleInfoColumnDatas moduleInfoColumnData = new ModuleInfoColumnDatas();
        ModuleInfoes moduleinfo = new ModuleInfoes();
        moduleinfo.setIsMenu(0);
        List<ModuleInfoes> moduleInfoes = moduleInfoesService.findList(moduleinfo);
        try {
            columnDatas.setIsDeleted(0);
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
            attributes.addFlashAttribute("message", "添加权限成功");
            logger.info("新增权限完成");
        } catch (Exception e) {
            logger.error("新增权限报错：", e);
            attributes.addFlashAttribute("message", "添加权限报错");
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
        logger.info("跳转更新权限页面开始");
        ModelAndView mv = new ModelAndView("/columnDatas/form");
        ColumnDatas ColumnDatas = ColumnDatasService.get(id);
        mv.addObject("ColumnDatas", ColumnDatas);
        mv.addObject("action", "update");//跳转编辑的标示
        logger.info("跳转更新权限页面结束");
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
    public ModelAndView update(ColumnDatas ColumnDatas, RedirectAttributes attributes) {
        logger.info("更新权限开始");
        ModelAndView mv = new ModelAndView("redirect:/permissions/columnDatas/index");
        try {
            ColumnDatas.setIsNewRecord(false);
            ColumnDatasService.saveOrUpdate(ColumnDatas);
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "更新权限成功");
            logger.info("更新权限完成");
        } catch (Exception e) {
            logger.error("更新权限报错：", e);
            attributes.addFlashAttribute("message", "更新权限报错");
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
        logger.info("删除权限" + id);

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
     * 全选删除权限信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value = "delete-all", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        logger.info("删除所有权限开始");
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            for (Long id : ids) {
                ColumnDatasService.deleteOne(id);
            }
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除权限错误信息：" + e);
        }
        return map;
    }
}
