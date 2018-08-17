package com.qis.shiro;

import com.app.ShiroUser;
import com.qis.common.persistence.util.LoginUser;
import com.qis.common.util.Encodes;
import com.qis.service.system.ResourceService;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

public class ShiroDbRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
    protected ResourceService resourceService;
//    private KnLogDao logDao;
//    @Autowired
//    private KnEmployeeDao employeeDao;
//    @Autowired
//    private SystemService systemService;
    @Autowired
    private  HttpServletRequest request;

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
          SimpleAuthenticationInfo auth;
          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(new ShiroUser(1L,username,username), "123456", this.getName());
          return authcInfo;


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
//            //this.saveLog(user);//记录登录日志
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
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        // 单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        permissions.add("authc");
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

//    /**
//     * 设定Password校验的Hash算法与迭代次数.
//     */
//    @PostConstruct
//    public void initCredentialsMatcher() {
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ResourceService.HASH_ALGORITHM);
//        matcher.setHashIterations(ResourceService.HASH_INTERATIONS);
//        setCredentialsMatcher(matcher);
//    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

//    /**
//     * 记录登录日志
//     *
//     * @param @param user
//     * @return void
//     * @throws
//     * @Title: saveLog
//     * @author Jason
//     */
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