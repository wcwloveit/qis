package com.xinri.service.dictionary;

import com.qis.common.service.IBaseService;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.dictionary.DictionaryVo;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
public interface IDictionaryService extends IBaseService<Dictionary> {
    public List<DictionaryVo> findListByVo(DictionaryVo dictionaryVo);

    public AjaxStatus DeleteDic(Long id);

    public List<Dictionary> reads(String dicKey);

    public void batchSaveDictionary(List<Dictionary> list);

    public String getDicValue(String dicKey, String subKey);

//    public String getRemark(String dicKey, String subKey);

    public String getDicKey(String dicValue, String subKey);
}
