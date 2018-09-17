package com.xinri.dao.item;


import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.item.ItemColor;
import com.xinri.vo.item.ModeColorVo;

import java.util.List;

@MyBatisDao
public interface ItemColorMapper extends CrudDao<ItemColor> {
    List<ModeColorVo> findListByVo(ModeColorVo vo);
}
