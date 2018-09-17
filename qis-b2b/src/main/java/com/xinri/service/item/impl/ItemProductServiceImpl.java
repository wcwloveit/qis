package com.xinri.service.item.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;

import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.xinri.dao.item.ItemProductMapper;
import com.xinri.po.item.ItemProduct;
import com.xinri.service.item.IItemPicService;
import com.xinri.service.item.IItemProductService;
import com.xinri.vo.item.ItemProductVo;
import com.xinri.vo.item.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("itemProductService")
public class ItemProductServiceImpl extends CrudService<ItemProductMapper,ItemProduct> implements IItemProductService {

    @Autowired
    public IItemPicService itemPicService;

    /**
     * 分页列表
     * @param dt
     * @param searchParams
     * @return
     */
    @Override
    public DataTable<ItemVo> findListByDt(DataTable<ItemVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ItemVo vo = new ItemVo();
            List<ItemVo> configList = new ArrayList<ItemVo>();
            if ( searchParams!= null && searchParams.size() != 0) {
                //初始价格 最高
                if (searchParams.containsKey("endPrice")&&!Strings.isNullOrEmpty(searchParams.get("endPrice").toString().trim())) {
                    String endPrice = searchParams.get("endPrice").toString().trim();
                    vo.setEndPrice(BigDecimal.valueOf(Double.valueOf(endPrice)));
                }

                //初始价格 最低
                if (searchParams.containsKey("startPrice")&&!Strings.isNullOrEmpty(searchParams.get("startPrice").toString().trim())) {
                    String startPrice = searchParams.get("startPrice").toString().trim();
                    vo.setStartPrice(BigDecimal.valueOf(Double.valueOf(startPrice)));
                }

                //更新时间 开始日期
                if (searchParams.containsKey("endUpdatedOn")&&!Strings.isNullOrEmpty(searchParams.get("endUpdatedOn").toString().trim())) {
                    String endUpdatedOn = searchParams.get("endUpdatedOn").toString().trim();
                    vo.setEndUpdatedOn(endUpdatedOn);
                }

                //更新时间 结束日期
                if (searchParams.containsKey("startUpdatedOn")&&!Strings.isNullOrEmpty(searchParams.get("startUpdatedOn").toString().trim())) {
                    String startUpdatedOn = searchParams.get("startUpdatedOn").toString().trim();
                    vo.setStartUpdatedOn(startUpdatedOn);
                }

                //创建时间 结束日期
                if (searchParams.containsKey("endCreatedOn")&&!Strings.isNullOrEmpty(searchParams.get("endCreatedOn").toString().trim())) {
                    String endCreatedOn = searchParams.get("endCreatedOn").toString().trim();
                    vo.setEndCreatedOn(endCreatedOn);
                }

                //创建时间 开始日期
                if (searchParams.containsKey("startCreatedOn")&&!Strings.isNullOrEmpty(searchParams.get("startCreatedOn").toString().trim())) {
                    String startCreatedOn = searchParams.get("startCreatedOn").toString().trim();
                    vo.setStartCreatedOn(startCreatedOn);
                }


                //颜色名称
                if (searchParams.containsKey("colorName")&&!Strings.isNullOrEmpty(searchParams.get("colorName").toString().trim())) {
                    String colorName = searchParams.get("colorName").toString().trim();
                    vo.setColorName(colorName);
                }

                //颜色编号
                if (searchParams.containsKey("colorCode")&&!Strings.isNullOrEmpty(searchParams.get("colorCode").toString().trim())) {
                    String colorCode = searchParams.get("colorCode").toString().trim();
                    vo.setColorCode(colorCode);
                }

                //组织编号
                if (searchParams.containsKey("orgCode")&&!Strings.isNullOrEmpty(searchParams.get("orgCode").toString().trim())) {
                    String orgCode = searchParams.get("orgCode").toString().trim();
                    vo.setOrgCode(orgCode);
                }

                //车型规格
                if (searchParams.containsKey("modelSpecification")&&!Strings.isNullOrEmpty(searchParams.get("modelSpecification").toString().trim())) {
                    String modelSpecification = searchParams.get("modelSpecification").toString().trim();
                    vo.setModelSpecification(modelSpecification);
                }

                //车型编号
                if (searchParams.containsKey("modelCode")&&!Strings.isNullOrEmpty(searchParams.get("modelCode").toString().trim())) {
                    String modelCode = searchParams.get("modelCode").toString().trim();
                    vo.setModelCode(modelCode);
                }

                //车型名称
                if (searchParams.containsKey("modelName")&&!Strings.isNullOrEmpty(searchParams.get("modelName").toString().trim())) {
                    String modelName = searchParams.get("modelName").toString().trim();
                    vo.setModelName(modelName);
                }
            }
            vo.setPage(page);
            configList = dao.findListByVo(vo);
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }

    /**
     * 获取选择车型信息
     * @param itemId
     * @return
     */
    public ItemProductVo chooseItem(String itemId){
        ItemProductVo itemDetailVo=new ItemProductVo();
        try{
            ItemProduct itemDetail=new ItemProduct();
            itemDetail.setId(Long.valueOf(itemId));
            itemDetail.setIsDeleted(0);
            itemDetailVo=dao.getChooseInf(itemDetail);
        }catch(Exception e){
            e.printStackTrace();
        }

        return itemDetailVo;
    }

    /**
     * 物理删除
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Boolean removeOne(Long id) {
        boolean statu = false;
        try {
            dao.remove(id);
            statu = true;
        }catch (Exception e) {
            logger.error("删除出错了"+e.getMessage());
        }
        return statu;
    }

}