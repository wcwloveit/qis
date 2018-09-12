//package com.qis.shiro;
//
//import com.qis.service.RedisService;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authz.AuthorizationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//public class CustomRolesAuthorizationFilter extends AuthorizationFilter {
//    private HttpServletRequest request;
//    @Autowired
//    private RolesPermissionsService rolesPermissionsService;
//    @Resource
//    private RedisService redisClusterCache;
//
//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        Subject subject = getSubject(request, response);
//        this.request = (HttpServletRequest) request;
//        String localAddr = this.request.getRequestURI();
//        PrincipalCollection principals = subject.getPrincipals();
//        if (null == principals) {
//            return true;
//        }
//        Session session = subject.getSession();
//        Serializable sessionId = session.getId();
//
//        String userName = principals.toString();
//        String userName0 = userName.split("==")[0];
//        String userName1 = "";
//
//        Object cache = redisClusterCache.getCache(sessionId.toString() + "==" + localAddr + "==" + userName);
//
//        String[] rolesArray = (String[]) mappedValue;
//        if (rolesArray == null || rolesArray.length == 0) {
//            return true;
//        }
//
//        List<String> rolesList = Arrays.asList(rolesArray);
//
//        if ("admin-pc".equals(userName0) || "store-pc".equals(userName0)) {
//            if (null != cache) {
//                Set<String> cache1 = (Set<String>) cache;
//                boolean disjoint = Collections.disjoint(cache1, rolesList);
//                return !disjoint;
//            }
//            userName1 = userName.split("==")[1];
//        }
//
//        List<RolesPermissions> rolesPermissions;
//        if ("admin-pc".equals(userName0)) {
//            rolesPermissions = rolesPermissionsService.selectByAuserName(userName1);
//        }else if("store-pc".equals(userName0)){
//            rolesPermissions = rolesPermissionsService.selectBySuserName(userName1);
//        }else{
//            return true;
//        }
//        //当前用户具有的权限
//        Set<String> roles = rolesPermissions.stream().map(RolesPermissions::getRolesName).collect(Collectors.toSet());
//
//        redisClusterCache.putCacheWithExpireTime(sessionId.toString() + "==" + localAddr + "==" + userName, roles, 86400); //24小时过期
//
//        boolean disjoint = Collections.disjoint(roles, rolesList);
//        if (!disjoint) {
//            return true;
//        }
//
//        /*for (String aRolesArray : rolesArray) {
//            if (subject.hasRole(aRolesArray)) {
//                return true;
//            }
//        }*/
//        return false;
//    }
//}
