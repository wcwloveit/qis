package com.xinri.controller.roleLog;


import com.app.api.DataTable;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
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

public class LoginController extends BaseController {



    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String findLogList(){
        return "roleLog/roleLog";
    }

}
