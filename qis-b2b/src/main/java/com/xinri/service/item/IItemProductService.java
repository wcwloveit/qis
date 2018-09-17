package com.xinri.service.item;


import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.item.ItemProduct;
import com.xinri.vo.item.ItemVo;

import java.util.Map;

public interface IItemProductService extends IBaseService<ItemProduct> {
   public DataTable<ItemVo> findListByDt(DataTable<ItemVo> dt, Map<String, Object> searchParams);
   public Boolean removeOne(Long id);
}

