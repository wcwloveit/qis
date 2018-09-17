package com.xinri.controller.item;


import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.item.ItemColor;
import com.xinri.po.item.ItemMode;
import com.xinri.po.item.ItemProduct;
import com.xinri.service.item.IItemColorService;
import com.xinri.service.item.IItemModeService;
import com.xinri.service.item.IItemProductService;
import com.xinri.vo.item.ModeColorVo;
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
@RequestMapping(value="/itemColor")
public class ItemColorController extends BaseController {

    @Autowired
    private IItemModeService itemModeService;
    @Autowired
    private IItemProductService itemProductService;

    @Autowired
    private IItemColorService itemColorService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index-{productId}-{id}", method = RequestMethod.GET)
    public String getIndexPage(@PathVariable("id") Long id,
                               @PathVariable("productId") Long productId,Model model){
        ItemProduct product = new ItemProduct();
        product = itemProductService.get(productId);
        model.addAttribute("product",product);
        ItemMode mode = new ItemMode();
        mode = itemModeService.get(id);
        model.addAttribute("mode",mode);
        return "item/colorList";
    }

    /**
     * 分页列表
     * @param dt
     * @param request
     * @return
     */
    @RequestMapping(value = "list-{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataTable<ModeColorVo> getItemList(@PathVariable("id") Integer id,
                                              DataTable<ModeColorVo> dt, ServletRequest request){
        logger.info("配置id："+id+"获取颜色列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("modeId", id);
        DataTable<ModeColorVo> itemVoDt = itemColorService.findListByDt(dt, searchParams);
        logger.info("配置id："+id+"获取颜色列表结束");
        return itemVoDt;
    }

    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create-{productId}-{modeId}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable("productId") Long productId,
                               @PathVariable("modeId") Long modeId){
        ModelAndView mv = new ModelAndView("item/colorForm");

        ItemProduct product = new ItemProduct();
        product = itemProductService.get(productId);
        mv.addObject("product",product);

        ItemMode mode = new ItemMode();
        mode = itemModeService.get(modeId);
        mv.addObject("mode",mode);

        mv.addObject("action","create");

        return mv;
    }

    /**
     * 新建
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create-{productId}-{modeId}", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable("productId") Long productId,
                             @PathVariable("modeId") Long modeId,
                             ItemColor color, RedirectAttributes attributes) {
        logger.info("配置id" + modeId + "新增颜色开始");

        ModelAndView mv = new ModelAndView("redirect:/itemColor/index-" + productId + "-" +modeId);
        try {
            color.setItemModeId(modeId);
            itemColorService.saveOrUpdate(color);
            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","添加颜色成功");
            logger.info("配置id" + modeId + "新增颜色完成");
        } catch (Exception e) {
            logger.error("新增颜色报错：", e);
            attributes.addFlashAttribute("message", "添加颜色报错");
        }
        return mv;
    }



    /**
     * 更新状态
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update-{productId}-{modeId}/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("productId") Long productId,
                         @PathVariable("modeId") Long modeId,
                         @PathVariable("id") Long id, Model model) {
        ItemProduct product = new ItemProduct();
        product = itemProductService.get(productId);

        ItemMode mode = new ItemMode();
        mode = itemModeService.get(modeId);

        ItemColor color = new ItemColor();
        color = itemColorService.get(id);

        model.addAttribute("action", "update");//跳转编辑的标示
        model.addAttribute("product",product);//产品信息
        model.addAttribute("mode",mode);//配置信息
        model.addAttribute("color",color);//颜色信息

        return "item/colorForm";
    }

    /**
     * 更新
     * @param color
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/update-{productId}-{modeId}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("productId") Long productId,
                                         @PathVariable("modeId") Long modeId,
                                         ItemColor color, RedirectAttributes attributes) {
        logger.info("配置id" + modeId + "更新颜色开始");
        ModelAndView mv = new ModelAndView("redirect:/itemColor/index-" + productId + "-" +modeId);
        try {
            color.setIsNewRecord(false);
            itemColorService.saveOrUpdate(color);

            attributes.addFlashAttribute("success",true);
            attributes.addFlashAttribute("message","更新颜色成功");
            logger.info("配置id" + modeId + "更新颜色完成");
        } catch (Exception e) {
            logger.error("配置id" + modeId + "更新颜色报错：", e);
            attributes.addFlashAttribute("message", "更新颜色报错");
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
        return  itemColorService.deleteOne(id);
    }


}
