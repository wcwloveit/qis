package com.xinri.controller.log;


import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.logs.LoginLogs;
import com.xinri.service.logs.ILoginLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/log")

public class LoginLogController extends BaseController {
    @Autowired
    private ILoginLogsService loginLogsService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "log/list";
    }

    @RequestMapping(value = "List", method = RequestMethod.POST)
    @ResponseBody
    public DataTable<LoginLogs> findLogList(DataTable<LoginLogs> dt, ServletRequest request){
        logger.info("开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        DataTable<LoginLogs> logsDataTable = loginLogsService.findLogList(dt, searchParams);
        logger.info("结束");
        return logsDataTable;
    }



}
