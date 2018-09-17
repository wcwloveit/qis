package com.xinri.controller.item;



import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.item.ItemColor;
import com.xinri.po.item.ItemMode;
import com.xinri.po.item.ItemPic;
import com.xinri.po.item.ItemProduct;

import com.xinri.service.item.IItemColorService;
import com.xinri.service.item.IItemModeService;
import com.xinri.service.item.IItemPicService;
import com.xinri.service.item.IItemProductService;
import com.xinri.vo.item.ItemPicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/itemPic")
public class ItemPicController extends BaseController {

	@Autowired
	private IItemPicService itemPicService;

	@Autowired
	private IItemProductService itemProductService;

	@Autowired
	private IItemModeService itemModeService;

	@Autowired
	private IItemColorService itemColorService;



	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index-{type}-{id}", method = RequestMethod.GET)
	public String getIndexPage(@PathVariable("id") Long id,
							   @PathVariable("type") Integer type,Model model){
		if(type==2){//产品
			ItemProduct product = new ItemProduct();
			product = itemProductService.get(id);
			model.addAttribute("row","产品：" + product.getModelName() + "  图片列表");
			model.addAttribute("rowName", product.getModelName());
		}else if(type==3){//配置
			ItemMode mode = new ItemMode();
			mode = itemModeService.get(id);
			model.addAttribute("row","配置：" + mode.getBname() + "  图片列表");
			model.addAttribute("rowName",mode.getBname());
		}else if(type==4){//颜色
			ItemColor color = new ItemColor();
			color = itemColorService.get(id);
			model.addAttribute("row","颜色：" + color.getColorName() + "  图片列表");
			model.addAttribute("rowName",color.getColorName());
		}
		model.addAttribute("type",type);
		model.addAttribute("id",id);

		return "item/picList";
	}

	/**
	 * 分页列表
	 * @param dt
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list-{type}-{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataTable<ItemPicVo> getItemList(@PathVariable("id") Long id,
											@PathVariable("type") Integer type,
											DataTable<ItemPicVo> dt, ServletRequest request){
		logger.info("类型type：" + type + ",id:" + id + ",获取图片列表开始");
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//1.获取活动设置，只显示活动图片，显示活动+产品图片
		DataTable<ItemPicVo> itemVoDt = itemPicService.findListByDt(dt, searchParams, id, type);
		logger.info("类型type：" + type + ",id:" + id + ",获取图片列表结束");
		return itemVoDt;
	}


	/**
	 * 根据Id逻辑删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteOne-{id}",method = RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(@PathVariable("id") Long id) {
		return  itemPicService.deleteOne(id);
	}

	/**
	 * 根据Id设置默认
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "defaultOne-{id}",method = RequestMethod.POST)
	@ResponseBody
	public Boolean defaultById(@PathVariable("id") Long id) {
		return  itemPicService.defaultOne(id);
	}


	/**
	 * 根据Id取消默认
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancelOne-{id}",method = RequestMethod.POST)
	@ResponseBody
	public Boolean cancelById(@PathVariable("id") Long id) {
		return  itemPicService.cancelOne(id);
	}



	/**
	 * 新建图片
	 * @param files
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	@RequestMapping(value = "upImg-{type}-{id}", method = RequestMethod.POST)
	public ModelAndView importInvoiceExcel(@PathVariable("type") Integer type,
									  @PathVariable("id") Long id,
									  @RequestParam("files") MultipartFile files,
										   RedirectAttributes attributes){
		logger.info("类型type：" + type + ",id:" + id + ",新建图片开始");
		Boolean b = itemPicService.upload(files,type,id);
		ModelAndView mv = new ModelAndView("redirect:/itemPic/index-" + type + "-" + id);
		if(b){
			attributes.addFlashAttribute("success", true);
			attributes.addFlashAttribute("message", "新建图片成功！");
		}else{
			attributes.addFlashAttribute("message", "新建图片失败！");
		}
		logger.info("类型type：" + type + ",id:" + id + ",新建图片结束");
		return mv;
	}


	/**
	 * 根据活动id选择产品图片到活动图片
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "chooseProduct-{id}", method = RequestMethod.GET)
	public ModelAndView chooseProduct(@PathVariable("id") Long id){
		ModelAndView mv = new ModelAndView("item/chooseList");

		ItemPic pic = new ItemPic();
		pic.setPageCode(2);
		pic.setIsDeleted(0);

        //获取活动下产品的图片
		List<ItemPic> picList = new ArrayList<ItemPic>();
		picList = itemPicService.findAllList(pic);

		mv.addObject("picList",picList);
		logger.info("活动id：" + id + ",新建图片结束");
		return mv;
	}


	/**
	 * 活动选择产品图片
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "choosePic-{actId}-{id}",method = RequestMethod.POST)
	@ResponseBody
	public Boolean choosePic(@PathVariable("id") Long id,@PathVariable("actId") Long actId) {
		logger.info("活动id：" + id + ",选择产品图片id:" + actId + "开始");
		try {
			ItemPic pic = new ItemPic();
			pic = itemPicService.get(id);
			pic.setId(null);
			pic.setItemId(actId);
			pic.setPicSort(0);
			pic.setPageCode(1);
			pic.setParentId(id);
			pic.setUpdatedBy(null);
			pic.setUpdatedOn(null);
			itemPicService.saveOrUpdate(pic);
			logger.info("活动id：" + id + ",选择产品图片id:" + actId + "结束");
			return true;
		}catch (Exception e) {
			logger.error("活动选择产品图片出错了"+e.getMessage());
			return false;
		}

	}


}
