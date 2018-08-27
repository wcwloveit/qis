package com.xinri.service.baseDataTypes.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.baseDataTypes.BaseDataTypes;
import com.xinri.dao.baseDataTypes.BaseDataTypesMapper;
import com.xinri.service.baseDataTypes.IBaseDataTypesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
@Service("baseDataTypesService")
public class BaseDataTypesServiceImpl extends CrudService<BaseDataTypesMapper, BaseDataTypes> implements IBaseDataTypesService {

    @Override
    public DataTable<BaseDataTypes> findListByVo(DataTable<BaseDataTypes> dt, Map<String, Object> searchParams) {

        try {
            Page page = new Page(dt.pageNo() + 1, dt.getiDisplayLength());
            BaseDataTypes baseDataTypes = new BaseDataTypes();  //实体类
            List<BaseDataTypes> configList = new ArrayList<BaseDataTypes>(); //new list

            if (searchParams != null && searchParams.size() != 0) {
                //名称
                if (searchParams.containsKey("name") && !Strings.isNullOrEmpty(searchParams.get("name").toString().trim())) {
                    String name = searchParams.get("name").toString().trim();
                    baseDataTypes.setName(String.valueOf(name));
                }
                //值
                if (searchParams.containsKey("type_value") && !Strings.isNullOrEmpty(searchParams.get("type_value").toString().trim())) {
                    String typeValue = searchParams.get("type_value").toString().trim();
                    baseDataTypes.setTypeValue(typeValue);
                }
                //描述
                if (searchParams.containsKey("desc") && !Strings.isNullOrEmpty(searchParams.get("desc").toString().trim())) {
                    String desc = searchParams.get("desc").toString().trim();
                    baseDataTypes.setDescr(String.valueOf(desc));
                }
                //创建时间 开始日期
                if (searchParams.containsKey("startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("startCreatedOn").toString().trim();
                    baseDataTypes.setStartCreatedOn(startCreatedOn);
                }
                //创建时间 结束日期
                if (searchParams.containsKey("endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("endCreatedOn").toString().trim();
                    baseDataTypes.setEndCreatedOn(endCreatedOn);
                }
            }

            baseDataTypes.setPage(page);  //获取分页对象
            configList = dao.findList(baseDataTypes); //获取分页数据
            page.setData(configList);  //***
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错" + e.getMessage());
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