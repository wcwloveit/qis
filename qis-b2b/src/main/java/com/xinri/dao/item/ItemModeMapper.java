package com.xinri.dao.item;

import com.qis.common.persistence.CrudDao;
import com.xinri.po.item.ItemMode;
import com.xinri.vo.item.ItemModeVo;

import java.util.List;

public interface ItemModeMapper extends CrudDao<ItemMode> {
    List<ItemModeVo> findListByVo(ItemModeVo vo);
}