package com.xinri.service.user.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.po.departments.Departments;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.port.IPortService;
import com.xinri.vo.org.OrgListVo;
import com.xinri.vo.users.OAUsersVo;
import com.xinri.vo.users.UserListVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.user.Users;
import com.xinri.dao.user.UsersMapper;
import com.xinri.service.user.IUsersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p></p>
 * 类名:UsersServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("usersService")
public class UsersServiceImpl extends CrudService<UsersMapper,Users>  implements IUsersService{

    @Autowired
    private IPortService portService;

    @Autowired
    private IDepartmentsService departmentsService;

    @Override
    @Transactional(readOnly = false)
    public void syncUsers(List<OAUsersVo> oaUsersVos){
        if (oaUsersVos.isEmpty()) {
            String usersInfo = portService.sendPort("OAUser", "", new JSONObject());
            oaUsersVos = com.alibaba.fastjson.JSONObject.parseArray(usersInfo, OAUsersVo.class);
        }
        List<OAUsersVo> surDept = new ArrayList<>();
        List<Users> usersList = new ArrayList<>();
        try {
            //查找所有用户
            List<Users> sqlUsers=new ArrayList<>();
            sqlUsers=dao.findAllList();
            Map<String,Object> allUsers=new HashMap<String,Object>();
            for(Users u:sqlUsers){
                allUsers.put(u.getUserNo(),u);
            }
            //查找所有部门
            List<Departments> departments=new ArrayList<>();
            departments=departmentsService.findAllList();
            Map<String ,Object> allDept=new HashMap<>();
            for(Departments d:departments){
                allDept.put(d.getOaId(),d);
            }

            for (OAUsersVo vo : oaUsersVos) {

                Users users = new Users();

                users.setUserNo(vo.getWorkcode());

                if (allUsers.containsKey(vo.getUsername())) {//存在 更新
                    logger.info(vo.getUsername());
                } else {//不存在，插入

                    users.setDescr(vo.getStatus());
                    users.setUserName(vo.getWorkcode());
                    String aa = vo.getMobile();
                    if(aa!=null){
                        if (aa.length() >= 20)
                        {
                            aa = aa.substring(0, 20);
                        }
                    }

                    users.setMobilePhone(aa);
                    users.setDescFlexField1(vo.getDeptid()+"");
                    users.setUserNo(vo.getUsername());
                    users.setName(vo.getLastname());
                    users.setIsDeleted(0);
                    users.setIsEffective(0);
                    users.setPermissionOverFlag(0);
                    users.setCreatedOn(new Date());

                    Departments dept = new Departments();//获取部门id

                    dept=(Departments) allDept.get(vo.getDeptid()+"");

                    if(dept!=null){
                        users.setDepartmentId(dept.getId());
                        users.setOrganizationId(dept.getOrganizationId());
                    }else{
                        users.setDepartmentId(0L);
                        users.setOrganizationId(0L);
                    }

                    users.setPassword("123");
                    users.setUserUseType(0);
                    usersList.add(users);
                }
            }
            for(Users aa:usersList){
                if(aa.getUserName()==null){
                    logger.info("aa");
                }
            }
            if (!usersList.isEmpty()) {
                dao.insertUsersList(usersList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("组织插入错误：" + e);
        }

    }

    /**
     * 获取分部列表
     * @param searchParams
     * @param id
     * @param dt
     * @return
     */
    @Override
    public DataTable<UserListVo> getUserList(Map<String, Object> searchParams, String id, DataTable<UserListVo> dt){

        List<UserListVo> orgListVos=new ArrayList<>();
        Users sqlUser=new Users();
        Long odid = Long.parseLong(id.substring(0,id.length() - 1));
        sqlUser.setDepartmentId(odid);

        List<Users> deptList=new ArrayList<>();

        Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
        //获取模糊查询参数
        if (searchParams != null && searchParams.size() != 0) {
            //加入查询条件
            //名称
            if (searchParams.containsKey("User_name") && !Strings.isNullOrEmpty(searchParams.get("User_name").toString().trim())) {
                String name = searchParams.get("User_name").toString().trim();
                sqlUser.setName(name);
            }
            if (searchParams.containsKey("User_workcode") && !Strings.isNullOrEmpty(searchParams.get("User_workcode").toString().trim())) {
                String workcode = searchParams.get("User_workcode").toString().trim();
                sqlUser.setUserNo(workcode);
            }
            if (searchParams.containsKey("User_code") && !Strings.isNullOrEmpty(searchParams.get("User_code").toString().trim())) {
                String code = searchParams.get("User_code").toString().trim();
                sqlUser.setUserName(code);
            }
            if (searchParams.containsKey("User_status") && !Strings.isNullOrEmpty(searchParams.get("User_status").toString().trim())) {
                String type = searchParams.get("User_status").toString().trim();
                sqlUser.setIsEffective(Integer.parseInt(type));
            }
            if (searchParams.containsKey("User_mobile") && !Strings.isNullOrEmpty(searchParams.get("User_mobile").toString().trim())) {
                String mobile = searchParams.get("User_mobile").toString().trim();
                sqlUser.setMobilePhone(mobile);
            }
        }

        sqlUser.setPage(page);
        deptList=dao.findList(sqlUser);
        int i=1;
        for(Users u:deptList){
            UserListVo vo=new UserListVo();
            vo.setId(i);
            vo.setNo(u.getId()+"");
            vo.setLoginid(u.getUserNo());
            vo.setUserCode(u.getUserName());
            vo.setUserMobile(u.getMobilePhone());
            vo.setUserName(u.getName());
            vo.setUserStatus(u.getIsEffective()+"");
            i++;
            orgListVos.add(vo);
        }

        page.setData(orgListVos);
        dt.setiTotalDisplayRecords(page.getTotalSize());
        dt.setAaData(page.getData());

        return dt;
    }



    public static void main(String[] args) {
        Map<String ,Object> aa=new HashMap<>();
        aa.put("aa",123);
        aa.put("bb",234);
        Object cc=aa.get("aa");
        System.out.println(cc);
    }



}