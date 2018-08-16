package com.xinri.controller.dictionary;

import com.xinri.po.dictionary.Dictionary;
import com.xinri.service.dictionary.impl.DictionaryService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.dictionary.DictionaryVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {


    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getIndexPage() {
        return "dictionary/dictionaryTree";
    }

    /**
     * 返回符合jsTree要求格式的数据List
     *
     * @return List<DictionaryVo>
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List<DictionaryVo> getItemList() {
        DictionaryVo dictionaryVo = new DictionaryVo();
        List<DictionaryVo> dictionaryVos = dictionaryService.findListByVo(dictionaryVo);
        return dictionaryVos;
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
        map.put("dictionary", dictionaryService.get(id));
        map.put("category", getCategoryList());
        return map;
    }

    /**
     * 新建或修改
     *
     * @param 数据信息和是否有效
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(Dictionary dictionary) {
        if (dictionary.getId() != null) {
            dictionary.setIsNewRecord(false);
        }
        String path = "";
        if (dictionary.getDicPid() == null) {
            path = dictionary.getId() + ".";
            dictionary.setPath(path);
            dictionaryService.saveOrUpdate(dictionary);
        } else {
            dictionaryService.saveOrUpdate(dictionary);
            path = dictionary.getPath() + dictionary.getId() + ".";
            dictionary.setPath(path);
            dictionaryService.saveOrUpdate(dictionary);
        }
        ModelAndView mv = new ModelAndView("redirect:/dictionary/index");
        return mv;
    }

    /**
     * 返回类型为类的数据
     *
     * @return List<Dictionary>
     */
    @RequestMapping(value = "categoryList", method = RequestMethod.POST)
    @ResponseBody
    public List<Dictionary> getCategoryList() {
        Dictionary dictionary = new Dictionary();
        return dictionaryService.findList(dictionary);
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
        return dictionaryService.DeleteDic(id);
    }

    /**
     * 检查同级下是否存在同名(dicValue)的数据
     *
     * @param dicValue(值)，dicPid(父级编号)，status(是修改还是新增)，id
     * @return
     */
    @RequestMapping(value = "/checkExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean Check(String dicValue, Long dicPid, String status, Long id) {
        if (id != 0 && "update".equals(status) && dicValue.equals(dictionaryService.get(id).getDicValue())) {
            return true; //如果是修改且改之前和改之后的dicValue一样的话则直接返回true
        }
        Dictionary dictionary = new Dictionary();
        if (dicPid != 0) {
            dictionary.setDicPid(dicPid);
        }
        dictionary.setDicValue(dicValue);
        List<Dictionary> dictionaries = dictionaryService.findList(dictionary);
        if (CollectionUtils.isNotEmpty(dictionaries)) {
            return false;   //同级下存在同名(dicValue)的数据，返回false
        }
        return true;
    }


}
