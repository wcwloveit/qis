package com.xinri.controller.roleLog;



import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.app.api.DataTable;
import com.xinri.po.roleLog.OperationRecord;
import com.xinri.service.roleLog.IRoleLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/roleLog")

public class RoleLogController extends BaseController {

    @Autowired
    private IRoleLogService roleLogService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findLogList(){
        return "roleLog/roleLog";
    }

    @RequestMapping(value = "List", method = RequestMethod.POST)
    @ResponseBody
    public DataTable<OperationRecord> findLogList(DataTable<OperationRecord> dt,ServletRequest request){
        logger.info("开始");
        Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        DataTable<OperationRecord> operationRes = roleLogService.findLogList(dt, searchParams);
        logger.info("结束");
        return operationRes;
    }


}
