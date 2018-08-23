package com.xinri.controller.baseDataTypes;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.baseDataTypes.BaseDataTypes;
import com.xinri.service.baseDataTypes.IBaseDataTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/baseDataTypes")
public class baseDtaTypesController extends BaseController {

    @Autowired
    private IBaseDataTypesService baseDatasTypesService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findTypesList() {
        return "baseDataTypes/list";
    }

    /*
     * 分页列表
     * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<BaseDataTypes> getItemList(DataTable<BaseDataTypes> dt, ServletRequest request){
        logger.info("获取角色列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<BaseDataTypes> baseDatas = baseDatasTypesService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取角色列表结束");
        return baseDatas;
    }

    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/baseDataTypes/form");
        mv.addObject("action","create");
        return mv;
    }


    /**
     * 新建
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(BaseDataTypes baseDataTypes,
                               RedirectAttributes attributes) {
        logger.info("新增类型开始");
        ModelAndView mv = new ModelAndView("redirect:/baseDataTypes/index");
        try {
            baseDataTypes.setIsDeleted(0);
            baseDataTypes.setIsEffective(0);
            baseDatasTypesService.saveOrUpdate(baseDataTypes);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加类型成功");
            logger.info("新增类型完成");
        } catch (Exception e) {
            logger.error("新增类型报错：", e);
            attributes.addFlashAttribute("message", "添加类型报错");
        }
        return mv;
    }


    /**
     * 更新状态
     * @param id
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("/baseDataTypes/form");
        BaseDataTypes baseDataTypes = baseDatasTypesService.get(id);
        mv.addObject("baseDataTypes",baseDataTypes);
        mv.addObject("action", "update");//跳转编辑的标示
        return mv;
    }

    /**
     * 更新
     * @param zcActivity
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(BaseDataTypes baseDataTypes, RedirectAttributes attributes) {
        logger.info("更新类型开始");
        ModelAndView mv = new ModelAndView("redirect:/baseDataTypes/index");
        try {
            baseDataTypes.setIsNewRecord(false);
            baseDatasTypesService.saveOrUpdate(baseDataTypes);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新类型成功");
            logger.info("更新类型完成");
        } catch (Exception e) {
            logger.error("更新类型报错：", e);
            attributes.addFlashAttribute("message", "更新类型报错");
        }
        return mv;
    }

    /**
     * 根据Id逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteOne-{id}",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        return  baseDatasTypesService.deleteOne(id);
    }

    /**
     * 全选删除角色信息
     *
     * @return 返回跳转链接
     */
    @RequestMapping(value="delete-all",method=RequestMethod.POST) @ResponseBody
    public Map<String,Boolean> deleteAll(@RequestParam("ids") List<Long> ids){
        Map<String,Boolean> map=new HashMap<String,Boolean>();
        try{
            for (Long id:ids){
                baseDatasTypesService.deleteOne(id);
            }
            map.put("stat",true);
        }catch(Exception e){
            map.put("stat",false);
            logger.info("删除角色错误信息："+e);
        }
        return map;
    }





}
