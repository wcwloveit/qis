package com.xinri.service.item;


import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.item.ItemColor;
import com.xinri.vo.item.ModeColorVo;

import java.util.Map;


public interface IItemColorService extends IBaseService<ItemColor> {

    public DataTable<ModeColorVo> findListByDt(DataTable<ModeColorVo> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);
}

