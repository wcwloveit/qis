package com.xinri.service.departments.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.po.organizations.Organizations;
import com.xinri.service.organizations.IOrganizationsService;
import com.xinri.service.port.IPortService;
import com.xinri.service.user.IUsersService;
import com.xinri.vo.dept.OADepartmentVo;
import com.xinri.vo.org.OAOrgVo;
import com.xinri.vo.org.OrgListVo;
import com.xinri.vo.org.request.OAOrgRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.departments.Departments;
import com.xinri.dao.departments.DepartmentsMapper;
import com.xinri.service.departments.IDepartmentsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:DepartmentsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("departmentsService")
public class DepartmentsServiceImpl extends CrudService<DepartmentsMapper,Departments>  implements IDepartmentsService{

    @Autowired
    private IPortService portService;

    @Autowired
    private IOrganizationsService organizationsService;

    @Autowired
    private IUsersService usersService;

    /**
     * 同步OA组织
     * @param deptList
     */
    @Override
    @Transactional(readOnly = false)
    public void syncOADept(List<OADepartmentVo> deptList) {
        if (deptList.isEmpty()) {
            String deptInfo = portService.sendPort("OADept", "", new JSONObject());
            deptList = com.alibaba.fastjson.JSONObject.parseArray(deptInfo, OADepartmentVo.class);
        }
        List<OADepartmentVo> surDept = new ArrayList<>();
        List<Departments> departments = new ArrayList<>();
        try {
            for (OADepartmentVo vo : deptList) {

                Departments dep = new Departments();
                dep.setOaId(vo.getId() + "");

                Departments selectDept = new Departments();
                selectDept = dao.getByEntity(dep);
                if (selectDept != null) {//存在 更新
                    if (!selectDept.getName().equals(vo.getDepartmentname())) {

                    }
                } else {//不存在，插入

                    dep.setName(vo.getDepartmentname());
                    if(vo.getCanceled()==null||vo.getCanceled().equals("0")){
                        dep.setOaCanceled("0");
                        dep.setIsEffective(0);
                        dep.setEffectiveDateStart(new Date());
                    }else{
                        dep.setOaCanceled(vo.getCanceled());
                        dep.setIsEffective(1);
                        dep.setEffectiveDateEnd(new Date());
                    }

                    dep.setDescr(vo.getDepartmentmark());
                    dep.setDescFlexField1(vo.getParentDeptId() + "");
                    dep.setOaNo(vo.getDepartmentcode());
                    dep.setIsDeleted(0);
                    dep.setCode(vo.getId() + "");
                    dep.setCreatedOn(new Date());


                    dep.setIsEffective(0);
                    Organizations sqlOrg = new Organizations();

                    sqlOrg.setOaId(vo.getCompanyId() + "");
                    Organizations companyId = organizationsService.get(sqlOrg);//获取分部ID

                    dep.setOrganizationId(companyId.getId());
                    if (vo.getParentDeptId() == 0) {//OA最外层
                        dep.setDepthLevel(0); //第一层
                        dep.setParentDepartmentId(0L);
                        departments.add(dep);
                    } else {
                        Departments parentSql = new Departments();
                        parentSql.setOaId(vo.getParentDeptId() + "");
                        Departments parent = new Departments();
                        parent = dao.getByEntity(parentSql);
                        if (parent != null) {//父节点存在
                            dep.setDepthLevel(parent.getDepthLevel() + 1);
                            dep.setParentDepartmentId(parent.getId());
                            departments.add(dep);
                        } else {
                            surDept.add(vo);
                        }
                    }
                }
            }
            if (!departments.isEmpty()) {
                dao.insertDeptList(departments);
            }
            if (!surDept.isEmpty()) {//全部完成，进行插入
                syncOADept(surDept);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("组织插入错误：" + e);
        }
    }

    /**
     * 获取OA中正在使用的组织
     * @param departments
     * @return
     */
    @Override
    public List<Departments> getUserDept(Departments departments){
        return dao.getUserDept(departments);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String,Object> sealDept(Long id,Map<String,Object> map){
        Departments departments=dao.get(id);
        Departments updateDept=new Departments();
        updateDept.setId(id);
        if(departments.getIsEffective()==0){//封存
            List<Departments> sqlDeptList=new ArrayList<>();
            sqlDeptList=dao.findAllChildDept(id+"");
            if(sqlDeptList.size()>0){//存在子部门
                if(usersService.findAllDeptUsers(sqlDeptList).size()>0){
                    map.put("stat",false);
                    map.put("msg","该组织下存在用户！无法封存！");
                }
            }else {
                updateDept.setIsEffective(1);
                updateDept.setEffectiveDateEnd(new Date());
            }
        }else{

            updateDept.setIsEffective(0);
            updateDept.setEffectiveDateStart(new Date());
        }
        try{
            dao.update(updateDept);
            map.put("stat",true);
        }catch (Exception e){
            map.put("stat",false);
            map.put("msg","封存出错！");
            logger.error("封存部门出错！"+e);
        }
        return map;

    }



    /**
     * 获取分部列表
     * @param searchParams
     * @param id
     * @param dt
     * @return
     */
    @Override
    public DataTable<OrgListVo> getDeptList(Map<String, Object> searchParams, String id, DataTable<OrgListVo> dt){

        List<OrgListVo> orgListVos=new ArrayList<>();
        Departments sqlDept=new Departments();
        Long odid = Long.parseLong(id.substring(0,id.length() - 1));
        if(id.contains("d")){//部门
            sqlDept.setParentDepartmentId(odid);
        }else if(id.contains("o")){//分部
            sqlDept.setOrganizationId(odid);
            sqlDept.setParentDepartmentId(0L);
        }

        List<Departments> deptList=new ArrayList<>();

        Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
        //获取模糊查询参数
        if (searchParams != null && searchParams.size() != 0) {
            //加入查询条件
            //名称
            if (searchParams.containsKey("LIKE_name") && !Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())) {
                String name = searchParams.get("LIKE_name").toString().trim();
                sqlDept.setName(name);
            }
            if (searchParams.containsKey("LIKE_descr") && !Strings.isNullOrEmpty(searchParams.get("LIKE_descr").toString().trim())) {
                String descr = searchParams.get("LIKE_descr").toString().trim();
                sqlDept.setDescr(descr);
            }
            if (searchParams.containsKey("LIKE_code") && !Strings.isNullOrEmpty(searchParams.get("LIKE_code").toString().trim())) {
                String code = searchParams.get("LIKE_code").toString().trim();
                sqlDept.setCode(code);
            }
            if (searchParams.containsKey("LIKE_type") && !Strings.isNullOrEmpty(searchParams.get("LIKE_type").toString().trim())) {
                String type = searchParams.get("LIKE_type").toString().trim();
                sqlDept.setOaCanceled(type);
            }
        }

        sqlDept.setPage(page);
        deptList=dao.findList(sqlDept);
        int i=1;
        for(Departments d:deptList){
            OrgListVo vo=new OrgListVo();
            vo.setId(i);
            vo.setNo(d.getId()+"");
            vo.setOrgCode(d.getCode());
            vo.setOrgDescr(d.getDescr());
            vo.setOrgName(d.getName());
            vo.setOrgStatus(d.getOaCanceled());
            i++;
            orgListVos.add(vo);
        }

        page.setData(orgListVos);
        dt.setiTotalDisplayRecords(page.getTotalSize());
        dt.setAaData(page.getData());

        return dt;
    }


    /**
     * 创建部门
     * @param request
     * @param dept
     * @return
     */
    @Override
    public Departments createDeparments(OAOrgRequest request, Departments dept){
        Departments sqlDept=new Departments();
        String odid = request.getSupId().substring(0,request.getSupId().length() - 1);
        sqlDept=dao.get(Long.parseLong(odid));
        if(sqlDept!=null){//查到
            dept.setId(sqlDept.getId());
            dept.setOaNo(request.getOaNo());

            dept.setName(request.getName());
            dept.setDescr(request.getDescr());
            dept.setIsDeleted(0);
            dept.setIsEffective(0);
            dept.setCode(request.getCode());
            dept.setOaCanceled(0+"");
            dept.setOrganizationId(sqlDept.getOrganizationId());
            if(request.getType().equals("sib")){//同级
                dept.setDepthLevel(sqlDept.getDepthLevel()); //第一层
                dept.setParentDepartmentId(sqlDept.getParentDepartmentId());
            }else if(request.getType().equals("child")){//下级
                dept.setDepthLevel(sqlDept.getDepthLevel()); //第一层
                dept.setParentDepartmentId(sqlDept.getId());
            }

        }
        return dept;
    }


}