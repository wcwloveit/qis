package com.xinri.service.baseData.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.po.logs.LoginLogs;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.baseData.BaseDatas;
import com.xinri.dao.baseData.BaseDatasMapper;
import com.xinri.service.baseData.IBaseDatasService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:BaseDatasServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("baseDatasService")
public class BaseDatasServiceImpl extends CrudService<BaseDatasMapper,BaseDatas>  implements IBaseDatasService{

    @Override
    public DataTable<BaseDatas> findListByVo(DataTable<BaseDatas> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            BaseDatas baseDatas= new BaseDatas();  //实体类
            List<BaseDatas> configList = new ArrayList<BaseDatas>(); //new list
            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("BaseData_name") && !Strings.isNullOrEmpty(searchParams.get("BaseData_name").toString().trim())) {
                    String name = searchParams.get("BaseData_name").toString().trim();
                    baseDatas.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("BaseData_code") && !Strings.isNullOrEmpty(searchParams.get("BaseData_code").toString().trim())) {
                    String code = searchParams.get("BaseData_code").toString().trim();
                    baseDatas.setCode(String.valueOf(code));

                }
            }
//            //上级数据字典id
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("BaseData_baseDataTypeId") && !Strings.isNullOrEmpty(searchParams.get("BaseData_baseDataTypeId").toString().trim())) {
                    String parentBaseDataId = searchParams.get("BaseData_baseDataTypeId").toString().trim();
                    baseDatas.setParentBaseDataId(Long.valueOf(parentBaseDataId));
                }
            }

            //生效开始时间
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("BaseData_effectiveDateStart") && !Strings.isNullOrEmpty(searchParams.get("BaseData_effectiveDateStart").toString().trim())) {
                    String effectiveDateStart = searchParams.get("BaseData_effectiveDateStart").toString().trim();
                    //testOrdr1.setInitialPrice(Integer.valueOf(price));
                    baseDatas.setEffectiveDateStart(Date.valueOf(effectiveDateStart));
                }
            }

            //生效结束时间
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("BaseData_effectiveDateEnd") && !Strings.isNullOrEmpty(searchParams.get("BaseData_effectiveDateEnd").toString().trim())) {
                    String effectiveDateEnd = searchParams.get("BaseData_effectiveDateEnd").toString().trim();
                    baseDatas.setEffectiveDateStart(Date.valueOf(effectiveDateEnd));
                }
            }

            baseDatas.setPage(page);  //获取分页对象
            configList=dao.findList(baseDatas); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }
}