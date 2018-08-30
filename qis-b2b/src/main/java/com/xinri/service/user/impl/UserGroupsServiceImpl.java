package com.xinri.service.user.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.xinri.dao.user.UserGroupsMapper;
import com.xinri.dao.user.UserUserGroupsMapper;
import com.xinri.dao.user.UsersMapper;
import com.xinri.po.user.UserGroups;
import com.xinri.po.user.UserUserGroups;
import com.xinri.po.user.Users;
import com.xinri.service.user.IUserGroupsService;
import com.xinri.service.user.IUserUserGroupsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.users.UserGroupsVo;
import com.xinri.vo.users.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UsersMapper userDao;

    @Autowired
    private UserUserGroupsMapper userUserGroupsMapper;

    @Autowired
    private IUserUserGroupsService userUserGroupsService;

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

    /**
     *用户组待添加用户列表
     * */
    @Override
    public DataTable QueryUserNotInRidList(DataTable<Users> dt, Map<String, Object> searchParams, Long roleId) {
        int pageNo=dt.pageNo()+1; //0
        int pageSize=dt.getiDisplayLength(); //10

        List<Users> list=new ArrayList<Users>();
       UserVo userVo=new UserVo();

      // Users users= new Users();  //实体类
        //名称
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                String name = searchParams.get("name").toString().trim();
                userVo.setName(String.valueOf(name));
            }
        }

        //登录名
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("userName") && !Strings.isNullOrEmpty(searchParams.get("userName").toString().trim())) {
                String userName = searchParams.get("userName").toString().trim();
                userVo.setUserName(String.valueOf(userName));
            }
        }

        //编号
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("userNo") && !Strings.isNullOrEmpty(searchParams.get("userNo").toString().trim())) {
                String userNo = searchParams.get("userNo").toString().trim();
                userVo.setUserNo(String.valueOf(userNo));
            }
        }

        //手机
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("mobilePhone") && !Strings.isNullOrEmpty(searchParams.get("mobilePhone").toString().trim())) {
                String mobilePhone = searchParams.get("mobilePhone").toString().trim();
                userVo.setMobilePhone(String.valueOf(mobilePhone));
            }
        }
       // list=userDao.findAllList(users);

        userVo.setRoleId(roleId);
         list = userUserGroupsMapper.getNotUserByUserGroupsId(userVo);
        dt.setiTotalDisplayRecords(list.size());
        int begin=pageSize*(pageNo-1);
        int end=pageSize*pageNo;
        if(begin>list.size()){
            list=new ArrayList<>();
        }else if(end>list.size()){
            list=list.subList(begin,list.size());
        }else{
            list=list.subList(begin,end);
        }
        dt.setAaData(list);
        return dt;


    }

    //加入
    @Transactional(readOnly=false)
    public AjaxStatus JoinRole(Long userGroupId, Long  userId) {
        AjaxStatus as = new AjaxStatus(true);
    try {
    //根据用户id获取用户数据
    UserUserGroups  userUserGroups = new UserUserGroups();
    userUserGroups.setUserId(userId); //添加用户id
    userUserGroups.setUserGroupId(userGroupId);//添加用户组id
    //调用 人员与用户组
    userUserGroupsService.saveOrUpdate(userUserGroups);
} catch (Exception e ){
        as = new AjaxStatus(false);
    logger.error("添加人员报错：", e);
}
    return  as;
    }

    //退出
    @Transactional(readOnly=false)
    public AjaxStatus LeaveRole(Long userGroupId,Long userId){
        AjaxStatus as=new AjaxStatus(true);
        try {
        //根据用户id获取用户数据
        UserUserGroups  userUserGroups = new UserUserGroups();
        userUserGroups.setUserId(userId); //添加用户id
        userUserGroups.setUserGroupId(userGroupId);//添加用户组id
            List<UserUserGroups> userUserGroupsPo =userUserGroupsService.findList(userUserGroups);
            if(userUserGroupsPo.size()>0){
            for(UserUserGroups userUserGroup:userUserGroupsPo){
                userUserGroupsService.delete(userUserGroups);
            }
        }
        } catch (Exception e ){
            as = new AjaxStatus(false);
            logger.error("退出人员报错：", e);
        }
        return as;
    }

    /**
     *查看用户组已经添加用户列表
     * */
    @Override
    public DataTable QueryUserByRidList(DataTable<Users> dt, Map<String, Object> searchParams, Long roleId) {
        int pageNo=dt.pageNo()+1; //0
        int pageSize=dt.getiDisplayLength(); //10

        List<Users> list=new ArrayList<Users>();
//        UserUserGroups userUserGroups = new UserUserGroups();
//        userUserGroups.setUserGroupId(Long.valueOf(roleId));
        UserVo userVo=new UserVo();
        userVo.setRoleId(roleId);

        //名称
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                String name = searchParams.get("name").toString().trim();
                userVo.setName(String.valueOf(name));
            }
        }

        //登录名
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("userName") && !Strings.isNullOrEmpty(searchParams.get("userName").toString().trim())) {
                String userName = searchParams.get("userName").toString().trim();
                userVo.setUserName(String.valueOf(userName));
            }
        }

        //编号
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("userNo") && !Strings.isNullOrEmpty(searchParams.get("userNo").toString().trim())) {
                String userNo = searchParams.get("userNo").toString().trim();
                userVo.setUserNo(String.valueOf(userNo));
            }
        }

        //手机
        if ( searchParams!= null && searchParams.size() != 0) {
            if (searchParams.containsKey("mobilePhone") && !Strings.isNullOrEmpty(searchParams.get("mobilePhone").toString().trim())) {
                String mobilePhone = searchParams.get("mobilePhone").toString().trim();
                userVo.setMobilePhone(String.valueOf(mobilePhone));
            }
        }

         list = userUserGroupsMapper.getUserByUserGroupsId(userVo);
        dt.setiTotalDisplayRecords(list.size());
        int begin=pageSize*(pageNo-1);
        int end=pageSize*pageNo;
        if(begin>list.size()){
            list=new ArrayList<>();
        }else if(end>list.size()){
            list=list.subList(begin,list.size());
        }else{
            list=list.subList(begin,end);
        }
        dt.setAaData(list);
        return dt;

    }
}