package com.xinri.controller.role;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.role.RoleClasses;
import com.xinri.service.role.IRoleClassesService;
import com.xinri.vo.role.RoleClassesVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/roleClass")
public class RoleClassController extends BaseController {
    @Autowired
    private IRoleClassesService roleClassesService;

    /*
    * 首页
    * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "roleClass/list";
    }

    /*
   * 分页列表
   * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<RoleClassesVo> getItemList(DataTable<RoleClassesVo> dt, ServletRequest request){
        logger.info("获取产品列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<RoleClassesVo> baseDatas = roleClassesService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取产品列表结束始");
        return baseDatas;
    }

    /**
     * 跳转新增
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/roleClass/roleClassForm");
        mv.addObject("action","create");
        return mv;
    }

    /**
     * 新增
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(RoleClasses roleClasses,
                             RedirectAttributes attributes) {
        logger.info("新增产品开始");
        ModelAndView mv = new ModelAndView("redirect:/roleClass/index"); //重定向
        try {
            roleClassesService.saveOrUpdate(roleClasses);
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
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        RoleClasses roleClasses = new RoleClasses();
        roleClasses = roleClassesService.get(id);
        model.addAttribute("action", "update");//跳转编辑的标示
        model.addAttribute("roleClasses",roleClasses);//商品信息
        return "/roleClass/roleClassForm";
    }

    /*
  * 更新
  * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateitemDetail(RoleClasses roleClasses, RedirectAttributes attributes) {
        logger.info("更新产品开始");
        ModelAndView mv = new ModelAndView("redirect:/roleClass/index");
        try {
            roleClasses.setIsNewRecord(false);
            roleClassesService.saveOrUpdate(roleClasses);
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
            roleClasses.setId(id); //自己的是 编号
            roleClasses.setIsDeleted(1);//把id为XX的哪一个实体类数据的IsDeleted 改为1
            roleClassesService.saveOrUpdate(roleClasses);//跟新实体类
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
     * 批量删除
     * @return 返回跳转链接
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        Map<String, Boolean> map = new HashMap();
        try {
            roleClassesService.batchDelete(ids);//batchDelete逻辑删除
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除账号错误信息：{}", e);
        }
        return map;
    }
}
