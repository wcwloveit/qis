package com.qis.service.system;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chirs@zhoujin.com (Chirs Chou)
 */
@Component @Transactional(readOnly=true) public class ResourceService {
    public static final String HASH_ALGORITHM="SHA-1";
    public static final int HASH_INTERATIONS=1024;
    public static final int SALT_SIZE=8;
    private static Logger logger=LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private  HttpServletRequest request;


    private CacheManager cacheManager;


//    public Boolean changePassword(String password,Long id){
//        KnUser u=userDao.findOne(id);
//        byte[] hashPassword=Digests.sha1(password.getBytes(),Encodes.decodeHex(u.getSalt()),HASH_INTERATIONS);
//        u.setPassword(Encodes.encodeHex(hashPassword));
//        userDao.save(u);
//        return true;
//    }
    
//
//
//    private void entryptPassword(KnUser user){
//        byte[] hashPassword=Digests.sha1(user.getPlainPassword().getBytes(),Encodes.decodeHex(user.getSalt()),HASH_INTERATIONS);
//        user.setPassword(Encodes.encodeHex(hashPassword));
//    }

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
//    @Transactional(readOnly=false) public Map<String,String> ChangePassword(String oldPassword,String newPassword) throws Exception{
//        Map map=new HashMap();
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