package com.xinri.service.organizations.impl;
import com.app.api.DataTable;
import com.app.api.JsTree;
import com.app.api.JsTreeState;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qis.common.persistence.Page;
import com.xinri.po.departments.Departments;
import com.xinri.service.departments.IDepartmentsService;
import com.xinri.service.port.IPortService;
import com.xinri.vo.org.OAOrgVo;
import com.xinri.vo.org.OrgInfoVo;
import com.xinri.vo.org.OrgListVo;
import net.sf.ehcache.search.expression.Or;
import net.sf.json.JSONObject;
import org.apache.commons.collections.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.organizations.Organizations;
import com.xinri.dao.organizations.OrganizationsMapper;
import com.xinri.service.organizations.IOrganizationsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * <p></p>
 * 类名:OrganizationsServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("organizationsService")
public class OrganizationsServiceImpl extends CrudService<OrganizationsMapper,Organizations>  implements IOrganizationsService{

    @Autowired
    private IPortService portService;

    @Autowired
    private IDepartmentsService departmentsService;

    /**
     * 同步OA组织
     * @param orgList
     */
    @Override
    @Transactional(readOnly = false)
    public void syncOAOrg(List<OAOrgVo> orgList){
        if(orgList.isEmpty()){
            String orgInfo=portService.sendPort("OAOrg","",new JSONObject());
            orgList= com.alibaba.fastjson.JSONObject.parseArray(orgInfo, OAOrgVo.class);
        }
        List<OAOrgVo> surOrg=new ArrayList<>();
        List<Organizations> organizations=new ArrayList<>();
        try{
            for(OAOrgVo vo:orgList){

                Organizations orgtion = new Organizations();
                orgtion.setOaId(vo.getId()+"");
                Organizations selectOrg=new Organizations();
                selectOrg=dao.getByEntity(orgtion);
                if(selectOrg!=null){//存在 更新
                    if(!selectOrg.getName().equals(vo.getOrgname())){

                    }
                }else{//不存在，插入
                    orgtion.setOaNo(vo.getOrgcode());

                    orgtion.setName(vo.getOrgname());
                    orgtion.setOaCanceled(vo.getCanceled());
                    orgtion.setDescr(vo.getOrgremark());
                    orgtion.setDescFlexField1(vo.getParentOrgId()+"") ;
                    orgtion.setIsDeleted(0);
				    orgtion.setIsEffective(0);
				    orgtion.setCreatedOn(new Date());


                    orgtion.setCode(vo.getId()+"");
                    if (vo.getParentOrgId() == 0)
                    {//OA最外层
                        orgtion.setDepthLevel(0); //第一层
                        orgtion.setParentOrganizationId(null);
                        organizations.add(orgtion);
                    }
                    else
                    {
                        Organizations parentSql=new Organizations();
                        parentSql.setOaId(vo.getParentOrgId()+"");
                        Organizations parent=new Organizations();
                        parent=dao.getByEntity(parentSql);
                        if (parent != null)
                        {//父节点存在
                            orgtion.setDepthLevel(parent.getDepthLevel()+1);
                            orgtion.setParentOrganizationId(parent.getId());
                            organizations.add(orgtion);
                        }else{
                            surOrg.add(vo);
                        }
                    }
                }
            }
            if(!organizations.isEmpty()){
                dao.insertOrgList(organizations);
            }
            if(!surOrg.isEmpty()){//全部完成，进行插入
                syncOAOrg(surOrg);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("组织插入错误："+e);
        }

    }

    /**
     * 获取组织树
     * @param supId
     * @return
     */
    @Override
    public List<JsTree> getOrgTree(String supId){
        List<JsTree> jsTrees=new ArrayList<>();
        try{
            if(supId==null){//初次加载
                List<Organizations> organizations=new ArrayList<>();
                Organizations sqlOrg=new Organizations();
                sqlOrg.setDepthLevel(0);
                organizations=dao.initOrgList(sqlOrg);
                jsTrees=setJsTree("#",organizations,jsTrees);
            }else{//点击加载
                String odid = supId.substring(0,supId.length() - 1);
                List<Departments> departments=new ArrayList<>();
                if(supId.contains("o")){//分部
                    //获取是否有下级分部
                    Organizations sqlOrg=new Organizations();
                    sqlOrg.setParentOrganizationId(Long.parseLong(odid));
                    List<Organizations> organizations=new ArrayList<>();
                    organizations=dao.initOrgList(sqlOrg);
                    jsTrees=setJsTree(supId,organizations,jsTrees);
                    //获取是否有下级部门
                    Departments dept=new Departments();
                    dept.setOrganizationId(Long.parseLong(odid));
                    dept.setParentDepartmentId(0L);
                    departments=departmentsService.getUserDept(dept);
                    jsTrees=setJsTree(supId,departments,jsTrees);
                }else if(supId.contains("d")){//部门
                    //获取是否有下级部门
                    Departments dept=new Departments();
                    dept.setParentDepartmentId(Long.parseLong(odid));
                    departments=departmentsService.getUserDept(dept);
                    jsTrees=setJsTree(supId,departments,jsTrees);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("组织树错误："+e+",出错ID为："+supId);
        }

        return jsTrees;

    }

    private List<JsTree> setJsTree(String supId,List infos,List<JsTree> treeList){
        if (infos.isEmpty()) {
            return treeList;
        }
        //获取所有分部
//        List<Organizations> orgs=new ArrayList<>();
//        orgs=dao.initOrgList(new Organizations());
//        Map<Long,Long> orgMap=new HashMap<>();
//        for(Organizations o:orgs){
//            orgMap.put(o.getId(),o.getParentOrganizationId());
//        }
//        //获取所有部门
//        List<Departments> depts=new ArrayList<>();
//        depts=departmentsService.getUserDept(new Departments());
//        Map<Long,Long> deptMap=new HashMap<>();
//        for(Departments d:depts){
//            deptMap.put(d.getId(),d.getParentDepartmentId());
//        }

        for(Object info : infos){
            JsTree jsTree=new JsTree();
            JsTreeState state = new JsTreeState();
            state.setSelected(false);
            state.setOpened(false);

            if(info instanceof Organizations){
                Organizations o=(Organizations)info;
                jsTree.setId(o.getId()+"o");
                jsTree.setText(o.getName());

                jsTree.setParent(supId);
                jsTree.setTypes("o");

                Organizations sqlOrg=new Organizations();
                sqlOrg.setParentOrganizationId(o.getId());
                jsTree.setChildren(true);
                //不存在子节点
                if(dao.initOrgList(sqlOrg)!=null){//不存在子分部
                    Departments dept=new Departments();
                    dept.setOrganizationId(o.getId());
                    if(departmentsService.getUserDept(dept)==null){
                        jsTree.setChildren(false);
                    }
                }
            }else if(info instanceof Departments){
                Departments d=(Departments)info;
                jsTree.setId(d.getId()+"d");
                jsTree.setTypes("p");
                jsTree.setParent(supId);
                jsTree.setText(d.getName());
                jsTree.setChildren(true);
                Departments dept=new Departments();
                dept.setParentDepartmentId(d.getId());
                if(departmentsService.getUserDept(dept).size()==0){
                    jsTree.setChildren(false);
                }

            }
            treeList.add(jsTree);
        }
        return treeList;
    }


    /**
     * 获取显示
     * @param id
     * @return
     */
    public OrgInfoVo read(String id){
        OrgInfoVo vo=new OrgInfoVo();
        String odid = id.substring(0,id.length() - 1);
        if(id.contains("o")) {//分部
            Organizations o=new Organizations();
            o=dao.get(Long.parseLong(odid));
            vo.setOaNo(o.getOaNo());
            vo.setU9No(o.getU9No());
            vo.setName(o.getName());
            vo.setDescr(o.getDescr());
            vo.setCode(o.getCode());
            if(o.getParentOrganizationId()!=null) {
                vo.setParentOrgName(dao.get(o.getParentOrganizationId()).getName());
            }
        }else if(id.contains("d")){
            Departments d=new Departments();
            d=departmentsService.get(Long.parseLong(odid));
            vo.setOaNo(d.getOaNo());
            vo.setU9No(d.getU9No());
            vo.setName(d.getName());
            vo.setDescr(d.getDescr());
            vo.setCode(d.getCode());
            vo.setParentOrgName(dao.get(d.getOrganizationId()).getName());
            if(d.getParentDepartmentId()!=0L){
                vo.setParentDeptName(departmentsService.get(d.getParentDepartmentId()).getName());
            }

        }
        return vo;
    }

    /**
     * 获取分部列表
     * @param searchParams
     * @param id
     * @param dt
     * @return
     */
    @Override
    public DataTable<OrgListVo> getOrgList(Map<String, Object> searchParams,String id,DataTable<OrgListVo> dt){
        List<OrgListVo> orgListVos=new ArrayList<>();
        Organizations organizations=new Organizations();
        Long odid = Long.parseLong(id.substring(0,id.length() - 1));
        organizations.setParentOrganizationId(odid);
        List<Organizations> orgList=new ArrayList<>();

        Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
        //获取模糊查询参数
        if (searchParams != null && searchParams.size() != 0) {
            //加入查询条件
            //名称
            if (searchParams.containsKey("LIKE_name") && !Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString().trim())) {
                String name = searchParams.get("LIKE_name").toString().trim();
                organizations.setName(name);
            }
            if (searchParams.containsKey("LIKE_descr") && !Strings.isNullOrEmpty(searchParams.get("LIKE_descr").toString().trim())) {
                String descr = searchParams.get("LIKE_descr").toString().trim();
                organizations.setDescr(descr);
            }
            if (searchParams.containsKey("LIKE_code") && !Strings.isNullOrEmpty(searchParams.get("LIKE_code").toString().trim())) {
                String code = searchParams.get("LIKE_code").toString().trim();
                organizations.setCode(code);
            }
            if (searchParams.containsKey("LIKE_type") && !Strings.isNullOrEmpty(searchParams.get("LIKE_type").toString().trim())) {
                String type = searchParams.get("LIKE_type").toString().trim();
                organizations.setOaCanceled(type);
            }
        }

        organizations.setPage(page);
        orgList=dao.findList(organizations);
        int i=1;
        for(Organizations o:orgList){
            OrgListVo vo=new OrgListVo();
            vo.setId(i);
            vo.setNo(o.getId()+"");
            vo.setOrgCode(o.getCode());
            vo.setOrgDescr(o.getDescr());
            vo.setOrgName(o.getName());
            vo.setOrgStatus(o.getOaCanceled());
            i++;
            orgListVos.add(vo);
        }

        page.setData(orgListVos);
        dt.setiTotalDisplayRecords(page.getTotalSize());
        dt.setAaData(page.getData());

        return dt;
    }




}