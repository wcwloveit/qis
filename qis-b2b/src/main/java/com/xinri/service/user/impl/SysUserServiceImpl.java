package com.xinri.service.SysUser.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.dao.user.SysUserMapper;
import com.xinri.po.user.SysUser;
import com.xinri.service.user.ISysUserService;
import com.xinri.vo.users.SysUserVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("sysUserService")
public class SysUserServiceImpl extends CrudService<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public DataTable<SysUser> findList(DataTable<SysUser> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo() + 1, dt.getiDisplayLength());
            SysUser sysUser = new SysUser();  //实体类
            List<SysUser> configList = new ArrayList<SysUser>(); //new list
//            if (searchParams != null && searchParams.size() != 0) {
//                //名称
//                if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
//                    String name = searchParams.get("name").toString().trim();
//                    SysUser.setName(String.valueOf(name));
//                }
//                //编码
//                if (searchParams.containsKey("code") && !Strings.isNullOrEmpty(searchParams.get("code").toString().trim())) {
//                    String code = searchParams.get("code").toString().trim();
//                    sysUser.setCode(code);
//                }
//                //描述
//                if (searchParams.containsKey("descr") && !Strings.isNullOrEmpty(searchParams.get("descr").toString().trim())) {
//                    String descr = searchParams.get("descr").toString().trim();
//                    sysUser.setDescr(String.valueOf(descr));
//                }
//                //创建时间 开始日期
//                if (searchParams.containsKey("startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("startCreatedOn").toString().trim())) {
//                    String startCreatedOn = searchParams.get("startCreatedOn").toString().trim();
//                    SysUser.setStartCreatedOn(startCreatedOn);
//                }
//                //创建时间 结束日期
//                if (searchParams.containsKey("endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("endCreatedOn").toString().trim())) {
//                    String endCreatedOn = searchParams.get("endCreatedOn").toString().trim();
//                    SysUser.setEndCreatedOn(endCreatedOn);
//                }
//            }
            sysUser.setPage(page);  //获取分页对象
            configList = dao.findList(sysUser); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错" + e.getMessage());
        }
        return dt;
    }

    @Override
    public DataTable<SysUserVo> findListByDt(DataTable<SysUserVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            SysUserVo vo = new SysUserVo();
            List<SysUserVo> configList = new ArrayList<SysUserVo>();
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("name")&&!Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    vo.setName(name);
                }
                if (searchParams.containsKey("account")&&!Strings.isNullOrEmpty(searchParams.get("account").toString().trim())) {
                    String account = searchParams.get("account").toString().trim();
                    vo.setAccount(account);
                }
                //创建时间 开始日期
                if (searchParams.containsKey("startBirth")&&!Strings.isNullOrEmpty(searchParams.get("startBirth").toString().trim())) {
                    String startBirth = searchParams.get("startBirth").toString().trim();
                    vo.setStartBirth(startBirth);
                }
                //创建时间 结束日期
                if (searchParams.containsKey("endBirth")&&!Strings.isNullOrEmpty(searchParams.get("endBirth").toString().trim())) {
                    String endBirth = searchParams.get("endBirth").toString().trim();
                    vo.setEndBirth(endBirth);
                }

            }
            vo.setIsDeleted(0);
            vo.setPage(page);
            configList = dao.findListByVo(vo);
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("活动列表出错"+e.getMessage());
        }
        return dt;
    }
    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Boolean deleteOne(Long id) {
        boolean statu = false;
        try {
            dao.delete(id);
            statu = true;
        } catch (Exception e) {
            logger.error("删除出错了" + e.getMessage());
        }
        return statu;
    }

}