package com.xinri.dao.item;

import com.qis.common.persistence.CrudDao;
import com.xinri.po.item.ItemPic;
import com.xinri.vo.item.ItemPicVo;

import java.util.List;

public interface ItemPicMapper extends CrudDao<ItemPic> {
    List<ItemPicVo> findListByVo(ItemPicVo vo);
}