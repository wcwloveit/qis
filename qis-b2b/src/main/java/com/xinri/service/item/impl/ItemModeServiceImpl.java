package com.xinri.service.item.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;

import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.xinri.dao.item.ItemModeMapper;
import com.xinri.po.item.ItemMode;
import com.xinri.service.item.IItemModeService;
import com.xinri.vo.item.ItemModeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("itemModeService")
public class ItemModeServiceImpl extends CrudService<ItemModeMapper,ItemMode> implements IItemModeService {
    /**
     * 分页列表
     * @param dt
     * @param searchParams
     * @return
     */
    @Override
    public DataTable<ItemModeVo> findListByDt(DataTable<ItemModeVo> dt, Map<String, Object> searchParams) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ItemModeVo vo = new ItemModeVo();
            List<ItemModeVo> configList = new ArrayList<ItemModeVo>();
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("isDeleted")&&!Strings.isNullOrEmpty(searchParams.get("isDeleted").toString().trim())) {
                    String modelName = searchParams.get("isDeleted").toString().trim();
                    vo.setIsDeleted(Integer.valueOf(modelName));
                }

                if (searchParams.containsKey("productId")&&!Strings.isNullOrEmpty(searchParams.get("productId").toString().trim())) {
                    String productId = searchParams.get("productId").toString().trim();
                    vo.setProductId(Long.valueOf(productId));
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
     * 逻辑删除
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
        }catch (Exception e) {
            logger.error("删除出错了"+e.getMessage());
        }
        return statu;
    }
}