package com.xinri.service;

//import com.xinri.dao.user.SysUserMapper;
//import com.xinri.po.user.SysUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.kingnode.diva.security.utils.Digests;
import com.kingnode.diva.utils.Encodes;
import com.qis.ShiroUser;
import com.qis.service.RedisService;
import com.xinri.po.moduleInfo.ModuleInfoes;
import com.xinri.po.moduleInfo.RoleModuleInfos;
import com.xinri.po.permissions.Permissions;
import com.xinri.po.role.Roles;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.Users;
import com.xinri.service.moduleInfo.IModuleInfoPermissionsService;
import com.xinri.service.moduleInfo.IModuleInfoesService;
import com.xinri.service.permissions.IPermissionsService;
import com.xinri.service.user.IUsersService;
import com.xinri.service.user.impl.UsersServiceImpl;
import com.xinri.vo.redis.Module;
import com.xinri.vo.redis.Redis;
import com.xinri.vo.redis.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 夏善勇
 */
@Service("resourceService")
@Component
@Transactional(readOnly = true)
public class ResourceService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    private static Logger logger = LoggerFactory.getLogger(ResourceService.class);


    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private SysUserMapper sysUserDao;


    private CacheManager cacheManager;

    @Autowired
    private IModuleInfoesService moduleInfoesService;

    @Autowired
    private IModuleInfoPermissionsService moduleInfoPermissionsService;


    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUsersService usersService;


    public Redis getFromRedis(ShiroUser user) {
        String key = "";
        if (user.type == 1) {
            key = "SYS_USER" + user.id;
        } else {
            key = "USER" + user.id;
        }
        String value = redisService.getString(key);
        if (value == null) {
            return saveToRedis(user);
        } else {
            JSONObject obj = JSON.parseObject(value);
            Redis redis = JSONObject.parseObject(obj.toString(), Redis.class);
            return redis;
        }
    }

    public Redis saveToRedis(ShiroUser user) {
        Redis info = getInfo(user);
        String value = JSONObject.toJSONString(info, redisProfilter);
        String key = "";
        if (user.type == 1) {
            key = "SYS_USER" + user.id;
        } else {
            key = "USER" + user.id;
        }
        redisService.add(key, value);
        return info;
    }

    /**
     * 根据系统用户获取导航栏列表和权限
     *
     * @param user
     * @return
     */
    public Redis getInfo(ShiroUser user) {
        Redis info = new Redis();
        if (user.type == 1) {
            info = moduleInfoesService.getModulesBySysUserId(user.id);
        } else if (user.type == 2) {
            info = moduleInfoesService.getModulesByUserId(user.id);
        }
        if (info == null) return info;
        List<Roles> roles = info.getRoles();
        List<Long> roleIds = new ArrayList<>();
        for (Roles role : roles) {
            roleIds.add(role.getId());
        }
        List<Module> resources = info.getModuleInfoesList();
        for (Module resource : resources) {
            List<Permissions> permissions = permissionsService.getPermissionsByModuleIdandRoleId(roleIds, resource.getId());
            for (Permissions permission : permissions) {
                permission.setCode(resource.getCode() + "-" + permission.getCode());
            }
            resource.setPermissionList(permissions);
        }
        return info;
    }

    public List<Resource> getResource(){
        List<Resource> before = moduleInfoPermissionsService.getResource();
        return before;
    }

    PropertyFilter redisProfilter = new PropertyFilter() {

        @Override
        public boolean apply(Object object, String name, Object value) {
            if (name.equalsIgnoreCase("page") || name.equalsIgnoreCase("dbName") || name.equalsIgnoreCase("global")) {
                //false表示last字段将被排除在外
                return false;
            }
            return true;
        }
    };

    public static void main(String[] args) {

//        String a = Encodes.encodeHex(Digests.generateSalt(SALT_SIZE));
//        byte[] hashPassword=Digests.sha1("123456".getBytes(), Encodes.decodeHex(a),HASH_INTERATIONS);
//        String b =Encodes.encodeHex(hashPassword);
//        System.out.println(a);
//        System.out.println(b);
    }

    /**
     * 更新密码
     * 创建人 夏善勇 2018-8-28
     * @return
     */
//    public Boolean changePassword(String password,Long id){
//        SysUser u=sysUserDao.get(id);
//        byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(u.getSalt()),HASH_INTERATIONS);
//        u.setPassword(Encodes.encodeHex(hashPassword));
//        sysUserDao.update(u);
//        return true;
//    }


//    private void entryptPassword(SysUser user){
//        byte[] hashPassword=Digests.sha1(user.getPlainPassword().getBytes(),Encodes.decodeHex(user.getSalt()),HASH_INTERATIONS);
//        user.setPassword(Encodes.encodeHex(hashPassword));
//    }
//
//    /**
//     * 修改密码
//     *
//     * @param oldPassword 旧密码
//     * @param newPassword 新密码
//     *
//     * @return
//     *
//     * @throws Exception
//     */
//    @Transactional(readOnly=false) public Map<String,String> ChangePassword(String oldPassword, String newPassword) throws Exception{
//        Map map = new HashMap();
//        KnUser ku=userDao.findOne(Users.id());
//        KnUser knUser=new KnUser();
//        knUser.setLoginName(ku.getLoginName());
//        knUser.setPlainPassword(oldPassword);
//        knUser.setSalt(ku.getSalt());
//        //外部提供明文密码,就会生成新的加密码,否则不生成新的加密密码
//        entryptPassword(knUser);
//        //获取登录密码
//        if(knUser.getPassword().equals(ku.getPassword())){
//            ku.setPlainPassword(newPassword);
//            entryptPassword(ku);
//            userDao.save(ku);
//            map.put(Setting.RESULTCODE,Setting.SUCCESSSTAT);
//            map.put(Setting.MESSAGE,"密码修改成功");
//        }else{
//            map.put(Setting.RESULTCODE,Setting.FAIURESTAT);
//            map.put(Setting.MESSAGE,"原始密码错误");
//        }
//        return map;
//    }


}