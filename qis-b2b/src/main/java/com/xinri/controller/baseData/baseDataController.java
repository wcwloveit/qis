package com.xinri.controller.baseData;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.service.baseData.IBaseDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/BaseData")
public class baseDataController extends BaseController {

    @Autowired private IBaseDatasService baseDatasService;

    /*
        * 首页
        * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "baseData/list";
    }

    /*
    * 分页列表
    * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<BaseDatas> getItemList(DataTable<BaseDatas> dt, ServletRequest request){
        logger.info("获取产品列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<BaseDatas> baseDatas = baseDatasService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取产品列表结束始");
        return baseDatas;
    }
}
