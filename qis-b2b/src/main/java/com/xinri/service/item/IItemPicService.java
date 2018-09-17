package com.xinri.service.item;

import com.app.api.DataTable;
import com.qis.common.service.IBaseService;
import com.xinri.po.item.ItemPic;
import com.xinri.vo.item.ItemPicVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface IItemPicService extends IBaseService<ItemPic> {

    public Boolean upload(MultipartFile file, Integer pageCode, Long itemId);

    public void deletePic(String path);

    public DataTable<ItemPicVo> findListByDt(DataTable<ItemPicVo> dt, Map<String, Object> searchParams, Long id, Integer type);

    public Boolean deleteOne(Long id);

    public Boolean defaultOne(Long id);

    public Boolean cancelOne(Long id);
}

