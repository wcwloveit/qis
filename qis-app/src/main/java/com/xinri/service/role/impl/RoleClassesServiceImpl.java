package com.xinri.service.role.impl;
import com.app.Setting;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.util.DownExcel;
import com.qis.common.persistence.Page;
import com.qis.util.PathUtil;
import com.xinri.po.user.UserGroups;
import com.xinri.vo.role.RoleClassesVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.role.RoleClasses;
import com.xinri.dao.role.RoleClassesMapper;
import com.xinri.service.role.IRoleClassesService;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:RoleClassesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("roleClassesService")
public class RoleClassesServiceImpl extends CrudService<RoleClassesMapper,RoleClasses>  implements IRoleClassesService{


    @Override
    public  DataTable<RoleClassesVo>  findListByVo( DataTable<RoleClassesVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            RoleClassesVo roleClassesVo= new RoleClassesVo();  //实体类
            List<RoleClassesVo> configList = new ArrayList<RoleClassesVo>(); //new list

            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_name") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_name").toString().trim())) {
                    String name = searchParams.get("RoleClass_name").toString().trim();
                    roleClassesVo.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_code") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_code").toString().trim())) {
                    String code = searchParams.get("RoleClass_code").toString().trim();
                    roleClassesVo.setCode(String.valueOf(code));

                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("RoleClass_startCreatedOn").toString().trim();
                    roleClassesVo.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("RoleClass_endCreatedOn").toString().trim();
                    roleClassesVo.setEndCreatedOn(endCreatedOn);
                }
            }

            roleClassesVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(roleClassesVo); //获取分页数据
            page.setData(configList);  //***
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

        ///添加导出条件
        RoleClasses zxOrder = searchZxOrder(searchParams);
        List<RoleClasses> list = dao.findAllList(zxOrder);//导出所有
        //导出数据
        String excelTitle = "角色类型列表";
        String[] headerTitle = new String[]{"名称","编号","描述"};
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(headerTitle); //列头
        if(list!=null){
            int j = 0;
            for(RoleClasses obj : list){
                j++;
                arrayList.add(
                        new String[]{
                                obj.getName(),
                                obj.getCode(),
                                obj.getDescr()

                       });
           }
        }
        Map<String,List<String[]>> map = new HashMap();//导出excel 内容
        map.put(excelTitle, arrayList);
        DownExcel downExcel = DownExcel.getInstall();
        downExcel.downLoadFile(response, downExcel.exportXlsExcel(map, PathUtil.getRootPath() + Setting.BASEADDRESS + Setting.excelAddress
                , String.valueOf(System.currentTimeMillis())), excelTitle, true); //导出2003 excel

    }

    private RoleClasses searchZxOrder(Map<String, Object> roleClass) {
        RoleClasses roleClasses=new RoleClasses();

        if ( roleClass!= null && roleClass.size() != 0) {

            //名称
            if (roleClass.containsKey("RoleClass_name")&&
                    !Strings.isNullOrEmpty(roleClass.get("RoleClass_name").toString().trim())) {
                String name = roleClass.get("RoleClass_name").toString().trim();
                roleClasses.setName(name);
            }

            //编号
            if (roleClass.containsKey("RoleClass_code")&&
                    !Strings.isNullOrEmpty(roleClass.get("RoleClass_code").toString().trim())) {
                String code = roleClass.get("RoleClass_code").toString().trim();
                roleClasses.setCode(code);
            }
            //描述
            if (roleClass.containsKey("RoleClass_descr")&&
                    !Strings.isNullOrEmpty(roleClass.get("RoleClass_descr").toString().trim())) {
                String descr = roleClass.get("RoleClass_descr").toString().trim();
                roleClasses.setDescr(descr);
            }
            roleClasses.setIsDeleted(0);
        }
        return roleClasses;
    }
}