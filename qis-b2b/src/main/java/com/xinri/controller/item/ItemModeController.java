package com.xinri.controller.item;


import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.item.ItemMode;
import com.xinri.po.item.ItemProduct;
import com.xinri.service.item.IItemModeService;
import com.xinri.service.item.IItemProductService;
import com.xinri.vo.item.ItemModeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/itemMode")
public class ItemModeController extends BaseController {

    @Autowired
    private IItemProductService itemProductService;
    @Autowired
    private IItemModeService itemModeService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index-{id}", method = RequestMethod.GET)
    public String getIndexPage(@PathVariable("id") Long id, Model model){
        ItemProduct product = new ItemProduct();
        product = itemProductService.get(id);
        model.addAttribute("product",product);
        return "item/modeList";
    }

    /**
     * 分页列表
     * @param dt
     * @param request
     * @return
     */
    @RequestMapping(value = "list-{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataTable<ItemModeVo> getItemList(@PathVariable("id") Integer id,
                                             DataTable<ItemModeVo> dt, ServletRequest request){
        logger.info("产品id："+id+"获取配置列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        searchParams.put("isDeleted", 0);
        searchParams.put("productId", id);
        DataTable<ItemModeVo> itemVoDt = itemModeService.findListByDt(dt, searchParams);
        logger.info("产品id："+id+"获取车型列表结束");
        return itemVoDt;
    }




    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create-{productId}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable("productId") Long productId){
        ModelAndView mv = new ModelAndView("item/modeForm");

        ItemProduct product = new ItemProduct();
        product = itemProductService.get(productId);

        mv.addObject("product",product);
        mv.addObject("action","create");

        return mv;
    }

    /**
     * 新建
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create-{productId}", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable("productId") Long productId,
                             ItemMode itemMode, RedirectAttributes attributes) {
        logger.info("产品id" + productId + "新增配置开始");
        ModelAndView mv = new ModelAndView("redirect:/itemMode/index-" + productId);
        try {
            itemMode.setProductId(productId);
            itemModeService.saveOrUpdate(itemMode);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加配置成功");
            logger.info("产品id" + productId + "新增配置完成");
        } catch (Exception e) {
            logger.error("新增配置报错：", e);
            attributes.addFlashAttribute("message", "添加配置报错");
        }
        return mv;
    }



    /**
     * 更新状态
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update-{productId}/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("productId") Long productId,
                         @PathVariable("id") Long id, Model model) {
        ItemProduct product = new ItemProduct();
        product = itemProductService.get(productId);

        ItemMode mode = new ItemMode();
        mode = itemModeService.get(id);

        model.addAttribute("action", "update");//跳转编辑的标示
        model.addAttribute("product",product);//商品信息
        model.addAttribute("mode",mode);//商品信息

        return "item/modeForm";
    }

    /**
     * 更新
     * @param mode
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/update-{productId}", method = RequestMethod.POST)
    public ModelAndView updateitemDetail(@PathVariable("productId") Long productId,
                                         ItemMode mode, RedirectAttributes attributes) {
        logger.info("产品id" + productId + "更新配置开始");
        ModelAndView mv = new ModelAndView("redirect:/itemMode/index-" + productId);
        try {
            mode.setIsNewRecord(false);
            itemModeService.saveOrUpdate(mode);

            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新配置成功");
            logger.info("产品id" + productId + "更新配置完成");
        } catch (Exception e) {
            logger.error("产品id" + productId + "更新配置报错：", e);
            attributes.addFlashAttribute("message", "更新配置报错");
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
        return  itemModeService.deleteOne(id);
    }

}
