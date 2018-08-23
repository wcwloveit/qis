package com.xinri.service.user.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.vo.role.RoleClassesVo;
import com.xinri.vo.users.UserGroupsVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.UserGroups;
import com.xinri.dao.user.UserGroupsMapper;
import com.xinri.service.user.IUserGroupsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:UserGroupsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("userGroupsService")
public class UserGroupsServiceImpl extends CrudService<UserGroupsMapper,UserGroups>  implements IUserGroupsService{


    @Override
    public DataTable<UserGroupsVo> findListByVo(DataTable<UserGroupsVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            UserGroupsVo userGroupsVo= new UserGroupsVo();  //实体类
            List<UserGroupsVo> configList = new ArrayList<UserGroupsVo>(); //new list

            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userGroup_name") && !Strings.isNullOrEmpty(searchParams.get("userGroup_name").toString().trim())) {
                    String name = searchParams.get("userGroup_name").toString().trim();
                    userGroupsVo.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userGroup_code") && !Strings.isNullOrEmpty(searchParams.get("userGroup_code").toString().trim())) {
                    String code = searchParams.get("userGroup_code").toString().trim();
                    userGroupsVo.setCode(String.valueOf(code));

                }
            }

            //描述
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userGroup_descr") && !Strings.isNullOrEmpty(searchParams.get("userGroup_descr").toString().trim())) {
                    String descr = searchParams.get("userGroup_descr").toString().trim();
                    userGroupsVo.setDescr(String.valueOf(descr));

                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userGroup_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("userGroup_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("userGroup_startCreatedOn").toString().trim();
                    userGroupsVo.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("userGroup_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("userGroup_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("userGroup_endCreatedOn").toString().trim();
                    userGroupsVo.setEndCreatedOn(endCreatedOn);
                }
            }

            userGroupsVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(userGroupsVo); //获取分页数据
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