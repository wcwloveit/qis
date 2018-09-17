package com.xinri.service.item;

import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.item.ItemMode;
import com.xinri.vo.item.ItemModeVo;

import java.util.Map;


public interface IItemModeService extends IBaseService<ItemMode> {

    public DataTable<ItemModeVo> findListByDt(DataTable<ItemModeVo> dt, Map<String, Object> searchParams);

    public Boolean deleteOne(Long id);
}

