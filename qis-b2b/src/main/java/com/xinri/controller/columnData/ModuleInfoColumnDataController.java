package com.xinri.controller.columnData;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.moduleInfo.ModuleInfoColumnDatas;
import com.xinri.service.moduleInfo.IColumnDatasService;
import com.xinri.service.moduleInfo.IModuleInfoColumnDatasService;
import com.xinri.vo.columnData.ColumnDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/permissions/moduleColumn")
public class ModuleInfoColumnDataController extends BaseController {



    @Autowired
    private IModuleInfoColumnDatasService moduleInfoColumnDatasService;


    /*
    * 首页
    * */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "moduleColumnData/list";
    }

    /*
   * */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<ModuleInfoColumnDatas> getItemList(DataTable<ModuleInfoColumnDatas> dt, ServletRequest request){
        logger.info("获取列数据列表开始");

        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<ModuleInfoColumnDatas> baseDatas =moduleInfoColumnDatasService.findListA(dt, searchParams); //查询方法
        logger.info("获取数据列表结束");
        return baseDatas;
    }


}
