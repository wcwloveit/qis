package com.xinri.controller.baseData;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.baseDataTypes.BaseDataTypes;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.service.baseDataTypes.IBaseDataTypesService;
import com.xinri.service.baseData.IBaseDatasService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.jstree.JsTree;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/baseData")
public class baseDataController extends BaseController {

    @Autowired
    private IBaseDatasService baseDatasService;
    @Autowired
    private IBaseDataTypesService baseDatasTypesService;

    /*
     * 首页
     * */
    @RequestMapping(value = "index/{id}", method = RequestMethod.GET)
    public ModelAndView findLogList(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("/baseData/tree");
        mv.addObject("id", id);
        mv.addObject("name", baseDatasTypesService.get(id).getName());
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "list/{id}", method = RequestMethod.POST)
    public List<JsTree> list(@PathVariable("id") Long id) {
        return baseDatasService.getTree(id);
    }

    /**
     * 返回jsTree点选项的字典数据的详细信息和类型为类的数据
     *
     * @param id 资源ID
     */
    @RequestMapping(value = "read-category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map readAll(@PathVariable Long id) {
        Map map = new HashMap();
        Long baseDataTypeId = baseDatasService.get(id).getBaseDataTypeId();
        map.put("baseData", baseDatasService.get(id));
        map.put("category", getCategoryList(baseDataTypeId));
        map.put("types", getTypeList());
        return map;
    }

    /**
     * 返回类型为类的数据
     *
     * @return List<Dictionary>
     */
    @RequestMapping(value = "categoryList", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseDatas> getCategoryList(Long id) {
        return baseDatasService.findParents(id);
    }

    @RequestMapping(value = "typeList", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseDataTypes> getTypeList() {
        return baseDatasTypesService.findAllList();
    }

    /**
     * 新建或修改
     *
     * @param 数据信息和是否有效
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(BaseDatas baseData) {
        if (baseData.getId() != null) {
            baseData.setIsNewRecord(false);
        }
        if(baseData.getParentBaseDataId()==null){
            baseData.setParentBaseDataId(0L);
        }
        baseDatasService.saveOrUpdate(baseData);
        ModelAndView mv = new ModelAndView("redirect:/baseData/index/"+baseData.getBaseDataTypeId());
        return mv;
    }

    /**
     * 物理删除
     *
     * @param 要删除的数据的id
     * @return 状态信息
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxStatus delete(@PathVariable("id") Long id) {
        return baseDatasService.DeleteDic(id);
    }


    /**
     * 检查同级下是否存在同名(dicValue)的数据
     *
     * @param dicValue(值)，dicPid(父级编号)，status(是修改还是新增)，id
     * @return
     */
    @RequestMapping(value = "/checkExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean Check(String name, Long pid, String status, Long id,Long bid) {
        if (id != 0 && "update".equals(status) && name.equals(baseDatasService.get(id).getName())) {
            return true; //如果是修改且改之前和改之后的dicValue一样的话则直接返回true
        }
        BaseDatas baseData = new BaseDatas();
        baseData.setParentBaseDataId(pid);
        baseData.setBaseDataTypeId(bid);
        baseData.setName(name);
        List<BaseDatas> baseDatas = baseDatasService.findList(baseData);
        if (CollectionUtils.isNotEmpty(baseDatas)) {
            return false;   //同级下存在同名(dicValue)的数据，返回false
        }
        return true;
    }

}