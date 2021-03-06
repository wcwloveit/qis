package com.qis.shiro;



import com.app.Setting;
import com.kingnode.diva.utils.Encodes;
import com.qis.ShiroUser;
import com.qis.service.RedisService;
import com.qis.util.PathUtil;
import com.qis.util.Utils;
import com.xinri.po.logs.LoginLogs;
import com.xinri.po.permissions.Permissions;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.Users;
import com.xinri.service.ResourceService;
import com.xinri.service.logs.ILoginLogDetailsService;
import com.xinri.service.user.ISysUserService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.redis.Module;
import com.xinri.vo.redis.Redis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.ktab.KeyTab;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShiroDbRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
    protected ResourceService resourceService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IUsersService usersService;

    @Autowired
    private ILoginLogDetailsService loginLogDetailsService;

    //    private KnLogDao logDao;
//    @Autowired
//    private KnEmployeeDao employeeDao;
//    @Autowired
//    private SystemService systemService;
    private HttpServletRequest request;

    @Autowired
    private RedisService redisService;



    //    @Autowired
//    private SystemService systemService;

//    @Autowired
//    public void setLogDao(KnLogDao logDao) {
//        this.logDao = logDao;
//    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(((UsernamePasswordToken) token).getUsername());
        String password = String.valueOf(((UsernamePasswordToken) token).getPassword());
        SysUser users = new SysUser();
        users.setAccount(username);
        users.setIsDeleted(0);
        SysUser sysUser = sysUserService.get(users);
        if (sysUser != null) {
            byte[] salt = Encodes.decodeHex(sysUser.getSalt());
            SimpleAuthenticationInfo auth;
            ShiroUser shiroUser = new ShiroUser(sysUser.getId(), sysUser.getAccount(), sysUser.getName(), 1);
            resourceService.saveToRedis(shiroUser);
            auth = new SimpleAuthenticationInfo(shiroUser,
                    sysUser.getPassword(), ByteSource.Util.bytes(salt), getName());
            this.saveLog(shiroUser);//记录登录日志
            return auth;
        } else {
            Users user = new Users();
            user.setIsEffective(0);
            user.setIsDeleted(0);
            user.setUserUseType(0);
            user.setUserNo(username);
            Users user2 = usersService.get(user);
            if (user2 != null) {
                byte[] salt = Encodes.decodeHex(user2.getSalt());
                SimpleAuthenticationInfo auth;
                ShiroUser shiroUser = new ShiroUser(user2.getId(), user2.getUserNo(), user2.getName(), 2);
                shiroUser.setDepId(user2.getDepartmentId());
                shiroUser.setOrgId(user2.getOrganizationId());
                resourceService.saveToRedis(shiroUser);
                auth = new SimpleAuthenticationInfo(shiroUser,
                        user2.getPassword(), ByteSource.Util.bytes(salt), getName());
                this.saveLog(shiroUser);//记录登录日志
                return auth;
            } else {
                throw new UnknownAccountException();
            }
        }


        //   KnUser user = resourceService.FindUserByLoginName(((UsernamePasswordToken) token).getUsername());
//        if (user != null) {
//            if (ActiveType.DISABLE.equals(user.getStatus())) {
//                throw new DisabledAccountException();
//            }
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            KnEmployee emp = employeeDao.findByLoginName(user.getLoginName());
//            boolean loginFlag = false;
//            SimpleAuthenticationInfo auth;
//            //先不去oa  验证  需要验证oa时候    替换为    equals("OA"))
//            //            if (emp!=null&&emp.getUserSystem().equals("OA")) {
//            //新日  可能会有临时禁用的号  将来自系统改为 equals中的值即可
//            if (emp!=null&&("OA".equals(emp.getUserSystem())||"OA_TEMP".equals(emp.getUserSystem()))) {
//                String password = String.valueOf(((UsernamePasswordToken) token).getPassword());
//                loginFlag = systemService.loginOA(((UsernamePasswordToken) token).getUsername(), password);
//                if (loginFlag) {
//                    String xSimplePassword ="123456";
//                    ((UsernamePasswordToken) token).setPassword(xSimplePassword.toCharArray());
//                    auth = new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()), user.getPassword(), ByteSource.Util.bytes(salt), getName());
//                } else {
//                    auth = new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()), "xxxxxxx", ByteSource.Util.bytes(salt), getName());
//                }
//            }else {
//                auth = new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()), user.getPassword(), ByteSource.Util.bytes(salt), getName());
//
//            }

//            return auth;
//        } else {
//            throw new UnknownAccountException();
//        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SysUser shiroUser = (SysUser) principals.getPrimaryPrincipal();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        // 单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        permissions.add("authc");
        Redis info =resourceService.getFromRedis(shiroUser);
        List<Module> resources = info.getModuleInfoesList();
        for (Module resource : resources) {
            if (CollectionUtils.isNotEmpty(resource.getPermissionList())) {
                List<Permissions> permissionsList = resource.getPermissionList();
                for (Permissions permissions1 : permissionsList) {
                    permissions.add(permissions1.getCode());
                }
            }
        }
        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
//        for (KnRole role : resourceService.CacheRoles(shiroUser.id)) {
//            // System.out.println("===================>"+role.getCode()+","+role.getName());
//            // 基于Role的权限信息
//            info.addRole(role.getCode());
//            // 基于Permission的权限信息
//            info.addStringPermissions(role.getPermissionList());
//            //System.out.println("------------------->"+role.getPermissionList());
//        }

        return simpleAuthorizationInfo;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ResourceService.HASH_ALGORITHM);
        matcher.setHashIterations(ResourceService.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    /**
     * 记录登录日志
     * 创建人  魏严  2018.9.12
     * @param @param user
     * @return void
     * @throws
     * @Title: saveLog
     * @author Jason
     */
    @Transactional(readOnly = false)
    public void saveLog(ShiroUser shiroUser) {
        try {
            LoginLogs Login=new LoginLogs();
            Login.setUserId(shiroUser.getId()); //用户id
            String ip = SecurityUtils.getSubject().getSession().getHost(); //IP地址
           // String ip = PathUtil.getIpAddr(request);
            Login.setIpAddress(ip);
            Login.setDataTypeId(Long.valueOf(35));//35 数据库表示登入
            Login.setIsEffective(shiroUser.getType()); //用户类型：1系统管理员，2普通用户
            loginLogDetailsService.saveOrUpdate(Login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    @Transactional(readOnly = false)
//    public void saveLog(KnUser user) {
//
//        KnOperationLog log = new KnOperationLog();
//        log.setOperationType(Setting.LogOperationType.login.name());//删除设备
//        log.setOptionTime(System.currentTimeMillis());
//        log.setOptionId(user.getId());
//        log.setOptionName(user.getName());
//        StringBuffer content = new StringBuffer();
//        String ip = null;
//        try {
//            ip = Utils.getIpAddr(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //String ip = SecurityUtils.getSubject().getSession().getHost();
//        content.append("来自IP为[").append(ip).append("]的用户");
//        content.append("[").append(user.getName()).append("]");
//        content.append("在[").append(DateUtil.getInstance().getDateStr(new Date(), "yyyy-MM-dd HH:mm")).append("]做了[");
//        content.append(Setting.LogOperationType.login.getOption_type()).append("]").append("行为");
//        log.setContent(content.toString());
//        logDao.save(log);
//    }
}