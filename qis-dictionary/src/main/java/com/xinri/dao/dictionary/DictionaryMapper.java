package com.xinri.dao.dictionary;

import com.qis.common.persistence.CrudDao;
import com.qis.common.persistence.annotation.MyBatisDao;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.vo.dictionary.DictionaryVo;

import java.util.List;

@MyBatisDao
public interface DictionaryMapper extends CrudDao<Dictionary>{
    List<DictionaryVo> findListByVo(DictionaryVo vo);

    void batchInsert(List<Dictionary> list);
}

