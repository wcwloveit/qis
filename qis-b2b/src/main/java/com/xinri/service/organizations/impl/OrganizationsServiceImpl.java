package com.xinri.service.organizations.impl;
import com.xinri.service.port.IPortService;
import com.xinri.vo.org.OAOrgVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.organizations.Organizations;
import com.xinri.dao.organizations.OrganizationsMapper;
import com.xinri.service.organizations.IOrganizationsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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



}