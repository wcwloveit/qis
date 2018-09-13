package com.xinri.service.logs.impl;
import com.app.Setting;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.qis.util.DownExcel;
import com.qis.util.PathUtil;
import com.xinri.dao.logs.LoginLogsMapper;
import com.xinri.po.logs.LoginLogs;
import com.xinri.po.role.RoleClasses;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.Users;
import com.xinri.service.logs.ILoginLogDetailsService;
import com.xinri.vo.log.LoginLogsVo;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("loginLogDetailsService")
class LoginLogDetailsServiceImpl extends CrudService<LoginLogsMapper,LoginLogs> implements ILoginLogDetailsService {

    @Override
    public DataTable<LoginLogsVo> findListByVo(DataTable<LoginLogsVo> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            LoginLogsVo loginLogsVo= new LoginLogsVo();  //实体类
            SysUser sysUser=new SysUser();
            Users user = new Users();
            List<LoginLogsVo> configList = new ArrayList<LoginLogsVo>(); //new list
            //登录名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userName") && !Strings.isNullOrEmpty(searchParams.get("userName").toString().trim())) {
                    String userNo = searchParams.get("userName").toString().trim();
                    loginLogsVo.setUserNo(String.valueOf(userNo));
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

//            for (LoginLogsVo Loginvo: configList){
//                if (Loginvo.getDataTypeId() != null){
//                    if(Loginvo.getDataTypeId()==35){
//                        Loginvo.setDataTypeName("登入");
//                    }else if (Loginvo.getDataTypeId()==36){
//                        Loginvo.setDataTypeName("登出");
//                    }
//                }
//
//                if(Loginvo.getIsEffective() !=null){
//                    if (Loginvo.getIsEffective()==1){
//                        Loginvo.setIsEffectiveName("系统用户");
//                    }else if (Loginvo.getIsEffective()==2){
//                        Loginvo.setIsEffectiveName("普通用户");
//                    }
//                }
//
//            }
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }

    /**
     * 导出
     * 创建人 魏严 2018.9.11
     * @param response
     * @param searchParams
     */
    @Override
    public void exportExcel(HttpServletResponse response, Map<String, Object> searchParams) {
        DataTable dt = new DataTable();
        dt.setiDisplayStart(0);
        dt.setiDisplayLength(100000000);
        dt.setiSortCol_0("0");
        dt.setsSortDir_0("desc");

       SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CreatedOn;

        ///添加导出条件
        LoginLogsVo zxOrder = searchZxOrder(searchParams);
        List<LoginLogsVo> list =dao.findListByVo(zxOrder);    //导出数据
        String excelTitle = "登录日志列表";
        String[] headerTitle = new String[]{"登录名","姓名","IP地址","用户IP","创建时间"};
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(headerTitle); //列头
        if(list!=null){
            int j = 0;
            for(LoginLogsVo obj : list){
                //日期格式转换
                if(obj.getCreatedTime()!=null){
                    CreatedOn=a.format(obj.getCreatedOn());
                }else{
                    CreatedOn="0000-00-00 00:00:00";
                };
                j++;
                arrayList.add(
                        new String[]{

                                obj.getUserNo(),
                                obj.getName(),
                                obj.getIpAddress(),
                                String.valueOf(obj.getUserId()),
                                CreatedOn//创建日期

                        });
            }
        }
        Map<String,List<String[]>> map = new HashMap();//导出excel 内容
        map.put(excelTitle, arrayList);
        DownExcel downExcel = DownExcel.getInstall();
        downExcel.downLoadFile(response, downExcel.exportXlsExcel(map, PathUtil.getRootPath() + Setting.BASEADDRESS + Setting.excelAddress
                , String.valueOf(System.currentTimeMillis())), excelTitle, true); //导出2003 excel

    }

    private LoginLogsVo searchZxOrder(Map<String, Object> searchParams) {
        LoginLogsVo loginLogsVo= new LoginLogsVo();
        if ( searchParams!= null && searchParams.size() != 0) {

            //ip地址
            if (searchParams.containsKey("ipAddress")&&
                    !Strings.isNullOrEmpty(searchParams.get("ipAddress").toString().trim())) {
                String ipAddress = searchParams.get("ipAddress").toString().trim();
                loginLogsVo.setIpAddress(ipAddress);
            }

            //用户id
            if (searchParams.containsKey("userId")&&
                    !Strings.isNullOrEmpty(searchParams.get("userId").toString().trim())) {
                String userId = searchParams.get("userId").toString().trim();
                loginLogsVo.setUserId(Long.valueOf(userId));
            }
            //登录账号
            if (searchParams.containsKey("userName")&&
                    !Strings.isNullOrEmpty(searchParams.get("userName").toString().trim())) {
                String userName = searchParams.get("userName").toString().trim();
                loginLogsVo.setUserNo(String.valueOf(userName));
            }

            //姓名
            if (searchParams.containsKey("name")&&
                    !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                String name = searchParams.get("name").toString().trim();
                loginLogsVo.setName(String.valueOf(name));
            }

            loginLogsVo.setIsDeleted(0);
        }
        return loginLogsVo;
    }
}
