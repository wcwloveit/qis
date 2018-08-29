package com.xinri.service.user.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.dao.user.UserUserGroupsMapper;
import com.xinri.dao.user.UsersMapper;
import com.xinri.po.user.UserUserGroups;
import com.xinri.po.user.Users;
import com.xinri.service.user.IUserUserGroupsService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.role.RoleClassesVo;
import com.xinri.vo.users.UserGroupsVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.UserGroups;
import com.xinri.dao.user.UserGroupsMapper;
import com.xinri.service.user.IUserGroupsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public DataTable QueryUserNotInRidList(DataTable<Users> dt, Map<String, Object> searchParams, String roleId) {
        int pageNo=dt.pageNo()+1; //0
        int pageSize=dt.getiDisplayLength(); //10
//        String loginName="%%", name="%%", email="%%";
//        String  name="%%";
//        if(searchParams!=null&&searchParams.size()!=0){
////            if(searchParams.containsKey("LIKE_loginName")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_loginName").toString().trim())){
////                loginName="%"+searchParams.get("LIKE_loginName").toString().trim().toUpperCase()+"%";
////            }
//            if(searchParams.containsKey("LIKE_name")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())){
//                name="%"+searchParams.get("LIKE_name").toString().trim().toUpperCase()+"%";
//            }
////            if(searchParams.containsKey("LIKE_email")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_email").toString().trim())){
////                email="%"+searchParams.get("LIKE_email").toString().trim().toUpperCase()+"%";
////            }
//
//            //名称
////            if ( searchParams!= null && searchParams.size() != 0) {
////                if (searchParams.containsKey("userGroup_name") && !Strings.isNullOrEmpty(searchParams.get("userGroup_name").toString().trim())) {
////                    String name = searchParams.get("userGroup_name").toString().trim();
////                    userGroupsVo.setName(String.valueOf(name));
////                }
////            }
//        }
        List<Users> list=new ArrayList<Users>();

      //  Users users= new Users();  //实体类

        list=userDao.findAllList();

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
        List<UserUserGroups> userUserGroupsPo =userUserGroupsService.findAllList(userUserGroups);
       // List<UserUserGroups> userUserGroupsPo =userUserGroupsService.findByCondition(userUserGroups);
            if(userUserGroupsPo.size()>0){
                userUserGroupsService.delete(userUserGroups);
//            for(UserUserGroups userUserGroup:userUserGroupsPo){
////               Users user = userDao.get(userUserGroup.getUserId());
//                userUserGroupsService.deleteById( userUserGroup.getId());
//            }
        }
        } catch (Exception e ){
            as = new AjaxStatus(false);
            logger.error("退出人员报错：", e);
        }
        return as;
    }


    @Override
    public DataTable QueryUserByRidList(DataTable<Users> dt, Map<String, Object> searchParams, String roleId) {
        int pageNo=dt.pageNo()+1; //0
        int pageSize=dt.getiDisplayLength(); //10
//        String loginName="%%", name="%%", email="%%";
//        if(searchParams!=null&&searchParams.size()!=0){
//            if(searchParams.containsKey("LIKE_loginName")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_loginName").toString().trim())){
//                loginName="%"+searchParams.get("LIKE_loginName").toString().trim().toUpperCase()+"%";
//            }
//            if(searchParams.containsKey("LIKE_name")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())){
//                name="%"+searchParams.get("LIKE_name").toString().trim().toUpperCase()+"%";
//            }
//            if(searchParams.containsKey("LIKE_email")&&!Strings.isNullOrEmpty(searchParams.get("LIKE_email").toString().trim())){
//                email="%"+searchParams.get("LIKE_email").toString().trim().toUpperCase()+"%";
//            }
//        }
//        Users users= new Users();
//        List<Long> list2 = new ArrayList<Long>();
//        List<Users> list =new ArrayList<Users>();

//        List<UserUserGroups> list1 = userUserGroupsService.findAllList(userUserGroups);
//        List<Users> usersList = new ArrayList<Users>();
//
//        if(list1.size()>0){
//            for(UserUserGroups userUserGroup:list1){
//               Users user = userDao.get(userUserGroup.getUserId());
//                usersList.add(user);
//            }
//        }
//
//        for (int j=0;j<list1.size();j++){
//            Users li=userDao.get( list1.get(j).getUserId());
//            list.add(users);
//        }
        List<Users> list=new ArrayList<Users>();
        UserUserGroups userUserGroups = new UserUserGroups();
        userUserGroups.setUserGroupId(Long.valueOf(roleId));
         list = userUserGroupsMapper.getUserByUserGroupsId(userUserGroups);

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