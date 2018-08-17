package com.xinri.service.dictionary;

import com.qis.common.service.IBaseService;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.dictionary.DictionaryVo;

import java.util.List;

public interface IDictionaryService  extends IBaseService<Dictionary> {
    public List<DictionaryVo> findListByVo(DictionaryVo dictionaryVo);

    public AjaxStatus DeleteDic(Long id);

}
