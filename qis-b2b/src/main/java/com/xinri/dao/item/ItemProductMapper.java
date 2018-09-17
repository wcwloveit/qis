package com.xinri.dao.item;

import com.qis.common.persistence.CrudDao;
import com.xinri.po.item.ItemProduct;
import com.xinri.vo.item.ItemProductVo;
import com.xinri.vo.item.ItemVo;

import java.util.List;

public interface ItemProductMapper extends CrudDao<ItemProduct> {
    public List<ItemVo> findListByVo(ItemVo vo);

    public ItemProductVo getChooseInf(ItemProduct zcItemDetail);
}