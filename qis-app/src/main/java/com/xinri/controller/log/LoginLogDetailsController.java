package com.xinri.controller.log;

import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.service.logs.ILoginLogDetailsService;
import com.xinri.vo.log.LoginLogsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/log/login")
public class LoginLogDetailsController extends BaseController {

    @Autowired
    private ILoginLogDetailsService loginLogDetailsService;
    /*
   * 首页
   * */
    @RequiresPermissions("log-logins-index")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "log/loginList";
    }


    /*
     * 分页列表
     * */
    @RequiresPermissions("log-logins-list")
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<LoginLogsVo> getItemList(DataTable<LoginLogsVo> dt, ServletRequest request){
        logger.info("获取产品列表开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<LoginLogsVo> baseDatas = loginLogDetailsService.findListByVo(dt, searchParams); //查询方法
        logger.info("获取产品列表结束始");
        return baseDatas;
    }

}