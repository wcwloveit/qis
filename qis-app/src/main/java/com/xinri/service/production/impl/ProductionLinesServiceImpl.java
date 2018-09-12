package com.xinri.service.production.impl;
import com.app.api.DataTable;
import com.google.common.base.Strings;
import com.qis.common.persistence.Page;
import com.xinri.vo.ProductionLines.ProductionLinesVo;
import com.xinri.vo.role.RoleClassesVo;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.production.ProductionLines;
import com.xinri.dao.production.ProductionLinesMapper;
import com.xinri.service.production.IProductionLinesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * 类名:ProductionLinesServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("productionLinesService")
public class ProductionLinesServiceImpl extends CrudService<ProductionLinesMapper,ProductionLines>  implements IProductionLinesService{

    /**
     * 创建人 weiyan 创建日期 2018.9.7
     * @param dt
     * @param searchParams
     * @return
     */
    @Override
    public DataTable<ProductionLinesVo> findListByVo(DataTable<ProductionLinesVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ProductionLinesVo productionLinesVo= new ProductionLinesVo();  //实体类
            List<ProductionLinesVo> configList = new ArrayList<ProductionLinesVo>(); //new list

            //名称
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_name") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_name").toString().trim())) {
                    String name = searchParams.get("RoleClass_name").toString().trim();
                    productionLinesVo.setName(String.valueOf(name));
                }
            }
            //编号
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_code") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_code").toString().trim())) {
                    String code = searchParams.get("RoleClass_code").toString().trim();
                    productionLinesVo.setCode(String.valueOf(code));

                }
            }

            //备注
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_descr") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_descr").toString().trim())) {
                    String descr = searchParams.get("RoleClass_descr").toString().trim();
                    productionLinesVo.setDescr(String.valueOf(descr));

                }
            }

            //创建时间  开始日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_startCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("RoleClass_startCreatedOn").toString().trim();
                    productionLinesVo.setStartCreatedOn(startCreatedOn);
                }
            }

            //创建时间  结束日期
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("RoleClass_endCreatedOn") && !Strings.isNullOrEmpty(searchParams.get("RoleClass_endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("RoleClass_endCreatedOn").toString().trim();
                    productionLinesVo.setEndCreatedOn(endCreatedOn);
                }
            }

            productionLinesVo.setPage(page);  //获取分页对象
            configList=dao.findListByVo(productionLinesVo); //获取分页数据
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