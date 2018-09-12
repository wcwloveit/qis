package com.xinri.controller.sysUser;

import com.app.api.DataTable;

import com.kingnode.diva.security.utils.Digests;
import com.qis.common.util.Encodes;
import com.qis.common.web.BaseController;
import com.qis.common.web.Servlets;
import com.xinri.po.departments.Departments;
import com.xinri.po.role.Roles;
import com.xinri.po.user.SysUser;
import com.xinri.po.user.Users;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.role.IRolesService;
import com.xinri.service.user.ISysUserService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.users.SysUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.Setting.HASH_INTERATIONS;
import static com.app.Setting.SALT_SIZE;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Controller
@RequestMapping(value = "user/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IDepartmentsService departmentsService;

    /**
     * 首页
     * @return
     * 创建人 汪震 20180907
     */
    
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String findTypesList() {
        return "sysUser/list";
    }

    /**
     * 分页列表
     * @param dt
     * @param request
     * @return
     * 创建人 汪震 20180907
     */
    
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<SysUserVo> getItemList(DataTable<SysUserVo> dt, ServletRequest request) {
        logger.info("获取系统用户列表开始");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_"); //去除search_
        DataTable<SysUserVo> baseDatas = sysUserService.findListByDt(dt, searchParams); //查询方法
        logger.info("获取系统用户列表结束");
        return baseDatas;
    }

    /**
     * 获取所有系统用户
     * @return
     *  创建人 汪震 20180907
     */
    @ResponseBody
    @RequestMapping(value = "SysUser", method = RequestMethod.GET)
    public List<SysUser> getSysUser() {
        logger.info("获取所有系统用户");
        return sysUserService.findAllList();
    }

    /**
     * 跳转新增页面
     * @return
     * 创建人 汪震 20180907
     */
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        logger.info("创建系统用户开始");
        ModelAndView mv = new ModelAndView("/sysUser/form");
        mv.addObject("action", "create");
        Roles role = new Roles();
        role.setIsDeleted(0);
        List<Roles> roles = rolesService.findList(role);
        mv.addObject("roles", roles);
        Departments department = new Departments();
        department.setIsDeleted(0);
        List<Departments> departments = departmentsService.findList(department);
        mv.addObject("departments", departments);
        logger.info("创建系统用户结束");
        return mv;
    }

    /**
     * 新增
     * @param sysUser
     * @param attributes
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(SysUser sysUser,
                               RedirectAttributes attributes) {
        logger.info("新增系统用户开始");
        ModelAndView mv = new ModelAndView("redirect:/user/sysUser/index");

        SysUser user = new SysUser();
        user.setAccount(sysUser.getAccount());
        List<SysUser> sysUserList = sysUserService.findList(user);

        if (CollectionUtils.isNotEmpty(sysUserList)) {
            attributes.addFlashAttribute("message", "非法操作，请重新填写信息");
            return mv;
        }

        sysUser.setSalt(Encodes.encodeHex(Digests.generateSalt(SALT_SIZE)));
        byte[] hashPassword = Digests.sha1(sysUser.getPassword().getBytes(), Encodes.decodeHex(sysUser.getSalt()), HASH_INTERATIONS);
        sysUser.setPassword(Encodes.encodeHex(hashPassword));
        try {
            sysUserService.saveOrUpdate(sysUser);
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "添加系统用户成功");
            logger.info("新增系统用户完成");
        } catch (Exception e) {
            logger.error("新增系统用户报错：", e);
            attributes.addFlashAttribute("message", "添加系统用户报错");
        }
        return mv;
    }


    /**
     * 跳转更新页面
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") Long id) {
        logger.info("跳转更新系统用户页面开始");
        ModelAndView mv = new ModelAndView("/sysUser/form");

        SysUser sysUser = sysUserService.get(id);
        sysUser.setPassword(null);

        Date currentTime = sysUser.getBirthday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (currentTime != null) {
            String birth = formatter.format(currentTime);
            mv.addObject("birth", birth);
        }

        Roles role = new Roles();
        role.setIsDeleted(0);
        List<Roles> roles = rolesService.findList(role);

        Departments department = new Departments();
        department.setIsDeleted(0);
        List<Departments> departments = departmentsService.findList(department);

        mv.addObject("departments", departments);
        mv.addObject("roles", roles);
        mv.addObject("sysUser", sysUser);
        mv.addObject("action", "update");//跳转编辑的标示
        logger.info("跳转更新系统用户页面结束");
        return mv;
    }

    /**
     *更新
     * @param sysUser
     * @param attributes
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(SysUser sysUser, RedirectAttributes attributes) {
        logger.info("更新系统用户开始");
        ModelAndView mv = new ModelAndView("redirect:/user/sysUser/index");

        SysUser user = new SysUser();
        user = sysUserService.get(sysUser.getId());
        if (user==null) {
            attributes.addFlashAttribute("message", "非法操作，请重新填写信息");
            return mv;
        }
//        user.setAccount(sysUser.getAccount());
//        List<SysUser> sysUserList = sysUserService.findList(user);
//
//        if (CollectionUtils.isNotEmpty(sysUserList)) {
//            attributes.addFlashAttribute("message", "非法操作，请重新填写信息");
//            return mv;
//        }

        byte[] hashPassword = Digests.sha1(sysUser.getPassword().getBytes(), Encodes.decodeHex(user.getSalt()), HASH_INTERATIONS);
        sysUser.setPassword(Encodes.encodeHex(hashPassword));

        try {
            sysUser.setIsNewRecord(false);
            sysUserService.saveOrUpdate(sysUser);
            attributes.addFlashAttribute("success", true);
            attributes.addFlashAttribute("message", "更新系统用户成功");
            logger.info("更新系统用户完成");
        } catch (Exception e) {
            logger.error("更新系统用户报错：", e);
            attributes.addFlashAttribute("message", "更新系统用户报错");
        }
        return mv;
    }

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    
    @RequestMapping(value = "deleteOne/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteById(@PathVariable("id") Long id) {
        logger.info("删除系统用户" + id);
        return sysUserService.deleteOne(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * 创建人 汪震 20180907
     */
    
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> deleteAll(@RequestParam("ids") List<Long> ids) {
        logger.info("删除所有系统用户开始");
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            for (Long id : ids) {
                sysUserService.deleteOne(id);
            }
            map.put("stat", true);
        } catch (Exception e) {
            map.put("stat", false);
            logger.error("删除系统用户错误信息：" + e);
        }
        return map;
    }

    /**
     * 检查账号是否同名
     * @param account
     * @param id
     * @return
     * 创建人 汪震 20180907
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public Boolean check(@RequestParam("account") String account, @RequestParam("id") Long id) {
        if (id != 0 && account.equals(sysUserService.get(id).getAccount())) {
            return true;
        }
        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);
        Users user = new Users();
        user.setUserName(account);
        List<Users> usersList = usersService.findList(user);
        List<SysUser> sysUserList = sysUserService.findList(sysUser);

        if (CollectionUtils.isNotEmpty(usersList) || CollectionUtils.isNotEmpty(sysUserList)) {
            return false;
        } else {
            return true;
        }
    }
}
