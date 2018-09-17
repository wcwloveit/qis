package com.xinri.controller.item;




import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.item.ItemProduct;
import com.xinri.po.item.ItemPic;

import com.xinri.service.item.IItemProductService;
import com.xinri.service.item.IItemPicService;
import com.xinri.vo.item.ItemProductVo;
import com.xinri.vo.item.ItemVo;
import net.sf.json.JSONObject;
import org.activiti.bpmn.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/item")
public class ItemController extends BaseController {


	@Autowired
	private IItemProductService itemProductService;
	@Autowired
	private IItemPicService itemPicService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String getIndexPage(){
		return "item/itemList";
	}

	/**
	 * 分页列表
	 * @param dt
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public DataTable<ItemVo> getItemList(DataTable<ItemVo> dt, ServletRequest request){
        logger.info("获取产品列表开始");
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        searchParams.put("isDeleted", 0);
        DataTable<ItemVo> itemVoDt = itemProductService.findListByDt(dt, searchParams);
        logger.info("获取产品列表结束始");
        return itemVoDt;
	}

	/**
	 * 跳转新增
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView mv = new ModelAndView("item/itemForm");
		mv.addObject("action","create");
		return mv;
	}

    /**
     * 新建
     * @param attributes
     * @return
     */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(ItemProduct itemDetail,
							 RedirectAttributes attributes) {
        logger.info("新增产品开始");
		ModelAndView mv = new ModelAndView("redirect:/item/index");
		try {
			itemProductService.saveOrUpdate(itemDetail);
			attributes.addFlashAttribute("success",true);
			attributes.addFlashAttribute("message","添加产品成功");
            logger.info("新增产品完成");
		} catch (Exception e) {
			logger.error("新增产品报错：", e);
			attributes.addFlashAttribute("message", "添加产品报错");
		}
		return mv;
	}


	/**
	 * 更新状态
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		ItemProduct itemDetail = new ItemProduct();
		itemDetail = itemProductService.get(id);

		model.addAttribute("action", "update");//跳转编辑的标示
		model.addAttribute("itemDetail",itemDetail);//商品信息

		return "item/itemForm";
	}

    /**
     * 更新
     * @param itemDetail
     * @param attributes
     * @return
     */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateitemDetail(ItemProduct itemDetail, RedirectAttributes attributes) {
        logger.info("更新产品开始");
        ModelAndView mv = new ModelAndView("redirect:/item/index");
		try {
			itemDetail.setIsNewRecord(false);
			itemProductService.saveOrUpdate(itemDetail);

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
	 * 根据Id物理删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeOne",method = RequestMethod.POST)
	@ResponseBody
	public Boolean removeById(Long id) {
		return  itemProductService.removeOne(id);
	}

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value = {"deleteOne-{id}"}, method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject LogicDel(@PathVariable Long id){
        JSONObject json = new JSONObject();
        try{

                //逻辑删除商品信息
                ItemProduct itemDetail = new ItemProduct();
                itemDetail.setId(id);
                itemDetail.setUpdatedOn(new Date());
                itemDetail.setIsDeleted(1);
                Integer res = itemProductService.saveOrUpdate(itemDetail);

                ItemPic itemPic = new ItemPic();
                itemPic.setItemId(id);
                List<ItemPic> picList = itemPicService.findList(itemPic);
                if(picList.size()>0){
                    for(ItemPic item:picList){
                        //删除服务器上图片文件
//                        itemPicService.deletePic(item.getPicUrl());
                        //逻辑删除数据库中的图片信息
                        item.setUpdatedOn(new Date());
                        item.setIsDeleted(1);
                        itemPicService.saveOrUpdate(item);
                    }
                }
                json.put("code","200");
                json.put("msg","删除成功");

        }catch(Exception e){
            e.printStackTrace();
            json.put("code","8001");
            json.put("msg","操作失败，请联系管理员！");
        }
        return json;
    }


}
