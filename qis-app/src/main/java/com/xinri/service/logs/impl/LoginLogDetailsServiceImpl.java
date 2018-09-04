package com.xinri.service.logs.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.xinri.dao.logs.LoginLogsMapper;
import com.xinri.po.logs.LoginLogs;
import com.xinri.service.logs.ILoginLogDetailsService;
import com.xinri.vo.log.LoginLogsVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("loginLogDetailsService")
class LoginLogDetailsServiceImpl extends CrudService<LoginLogsMapper,LoginLogs> implements ILoginLogDetailsService {

    @Override
    public DataTable<LoginLogsVo> findListByVo(DataTable<LoginLogsVo> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            LoginLogsVo loginLogsVo= new LoginLogsVo();  //实体类
            List<LoginLogsVo> configList = new ArrayList<LoginLogsVo>(); //new list

            //登录名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userName") && !Strings.isNullOrEmpty(searchParams.get("userName").toString().trim())) {
                    String userName = searchParams.get("userName").toString().trim();
                    loginLogsVo.setUserName(String.valueOf(userName));
                }
            }
            //姓名
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    loginLogsVo.setName(String.valueOf(name));

                }
            }



            //IP地址
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("ipAddress") && !Strings.isNullOrEmpty(searchParams.get("ipAddress").toString().trim())) {
                    String ipAddress = searchParams.get("ipAddress").toString().trim();
                    loginLogsVo.setIpAddress(String.valueOf(ipAddress));

                }
            }

            //用户id
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userId") && !Strings.isNullOrEmpty(searchParams.get("userId").toString().trim())) {
                    String userId = searchParams.get("userId").toString().trim();
                    loginLogsVo.setUserId(Long.valueOf(userId));
                }
            }

//            //创建时间  开始日期
//            if ( searchParams!= null && searchParams.size() != 0) {
//                if (searchParams.containsKey("userGroup_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("userGroup_startCreatedOn").toString().trim())) {
//                    String startCreatedOn = searchParams.get("userGroup_startCreatedOn").toString().trim();
//                    userGroupsVo.setStartCreatedOn(startCreatedOn);
//                }
//            }
//
//            //创建时间  结束日期
//            if ( searchParams!= null && searchParams.size() != 0) {
//                if (searchParams.containsKey("userGroup_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("userGroup_endCreatedOn").toString().trim())) {
//                    String endCreatedOn = searchParams.get("userGroup_endCreatedOn").toString().trim();
//                    userGroupsVo.setEndCreatedOn(endCreatedOn);
//                }
//            }

            loginLogsVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(loginLogsVo); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }
}
