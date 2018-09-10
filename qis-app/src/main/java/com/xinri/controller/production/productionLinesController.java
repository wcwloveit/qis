package com.xinri.controller.production;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.po.organizations.Organizations;
import com.xinri.po.production.ProductionLines;
import com.xinri.po.role.RoleClasses;
import com.xinri.po.user.UserGroups;
import com.xinri.service.baseData.IBaseDatasService;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.service.production.IProductionLinesService;
import com.xinri.vo.ProductionLines.ProductionLinesVo;
import com.xinri.vo.role.RoleClassesVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/production/lines")
public class productionLinesController extends BaseController {
    @Autowired
    private IProductionLinesService productionLinesService;

    //组织
    @Autowired
    private IOrganizationsService organizationsService;

    //数据字典
    @Autowired
    private IBaseDatasService baseDatasService;

    /**创建人 weiyan 创建时间 2018.9.7
     * 首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList() {
        return "productionLines/list";
    }

    /**
     * 创建人 weiyan 创建时间 2018.9.7
     * 分页列表
     * @param dt
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<ProductionLinesVo> getItemList(DataTable<ProductionLinesVo> dt, ServletRequest request){
        logger.info("获取产品列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<ProductionLinesVo> baseDatas = productionLinesService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取产品列表结束始");
        return baseDatas;
    }

    /**
     * 创建人 魏严 创建时间 2018.9.10
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/productionLines/form");
        //组织下拉框
        List<Organizations> organizationsList=new ArrayList<Organizations>();
        Organizations organizations=new Organizations();
        organizations.setIsDeleted(0);
        organizationsList=organizationsService.findList(organizations);
        //系统下拉框
        List<BaseDatas> baseDatasList = new ArrayList<BaseDatas>();
        BaseDatas baseDatas=new BaseDatas();
        baseDatas.setBaseDataTypeId(Long.valueOf("10")); //基础数据类型为10表示所属系统
        baseDatas.setIsDeleted(0);
        baseDatasList=baseDatasService.findList(baseDatas);
        mv.addObject("organizationsList", organizationsList);//组织信息
        mv.addObject("baseDatasList", baseDatasList);//系统信息
        mv.addObject("action","create");
        return mv;
    }

    /**
     * 创建人 魏严 2018.9.10
     * 新增
     * @param productionLines
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(ProductionLines productionLines,
                             RedirectAttributes attributes) {
        logger.info("新增生产线开始");
        ModelAndView mv = new ModelAndView("redirect:/production/lines/index"); //重定向
        try {
            productionLinesService.saveOrUpdate(productionLines);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加生产线成功");
            logger.info("新增生产线完成");
        } catch (Exception e) {
            logger.error("新增生产线报错：", e);
            attributes.addFlashAttribute("message", "添加生产线报错");
        }
        return mv;
    }

    /**
     * 创建人 魏严  2018.9.10
     * 更新跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        ProductionLines productionLines = new ProductionLines();
        productionLines = productionLinesService.get(id);
        //组织下拉框
        List<Organizations> organizationsList=new ArrayList<Organizations>();
        Organizations organizations=new Organizations();
        organizations.setIsDeleted(0);
        organizationsList=organizationsService.findList(organizations);
        //系统下拉框
        List<BaseDatas> baseDatasList = new ArrayList<BaseDatas>();
        BaseDatas baseDatas=new BaseDatas();
        baseDatas.setBaseDataTypeId(Long.valueOf("10")); //基础数据类型为10表示所属系统
        baseDatas.setIsDeleted(0);
        baseDatasList=baseDatasService.findList(baseDatas);
        model.addAttribute("action", "update");//跳转编辑的标示
        model.addAttribute("productionLines", productionLines);//生产线信息
        model.addAttribute("organizationsList", organizationsList);//组织信息
        model.addAttribute("baseDatasList", baseDatasList);//系统信息
        return "/productionLines/form"; //跳转到更新页面
    }

    /**
     * 创建人 魏严 2018.9.10
     * 更新
     * @param productionLines
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateitemDetail(ProductionLines productionLines, RedirectAttributes attributes) {
        logger.info("更新产品开始");
        ModelAndView mv = new ModelAndView("redirect:/production/lines/index");
        try {
            productionLines.setIsNewRecord(false);
            productionLinesService.saveOrUpdate(productionLines);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新产品成功");
            logger.info("更新产品完成");
        } catch (Exception e) {
            logger.error("更新产品报错：", e);
            attributes.addFlashAttribute("message", "更新产品报错");
        }
        return mv;
    }

    /**
     * 创建人 weiyan 创建时间 2018.9.8
     * 逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value = {"delete-{id}"}, method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject LogicDel(@PathVariable Long id){
        JSONObject json = new JSONObject();
        try{
            //逻辑删除商品信息
            RoleClasses roleClasses = new RoleClasses();
            ProductionLines productionLines=new ProductionLines();
            productionLines.setId(id); //自己的编号
            productionLines.setIsDeleted(1);//把id为XX的哪一个实体类数据的IsDeleted 改为1
            productionLinesService.saveOrUpdate(productionLines);//更新实体类
            json.put("code","200");
            json.put("msg","删除成功");
        }catch(Exception e){
            e.printStackTrace();
            json.put("code","8001");
            json.put("msg","操作失败，请联系管理员！");
        }
        return json;
    }

    /**
     *  weiyan 创建时间 2018.9.8
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        Map<String, Boolean> map = new HashMap();
        try {
            productionLinesService.batchDelete(ids);//batchDelete逻辑删除
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除账号错误信息：{}", e);
        }
        return map;
    }



}
