package com.xinri.service.dictionary.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.dictionary.DictionaryMapper;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.service.dictionary.IDictionaryService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.dictionary.DictionaryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dictionaryService")
public class DictionaryService extends CrudService<DictionaryMapper, Dictionary> implements IDictionaryService {


    public int updateByCondition(Dictionary entity) {
        return 0;
    }

    public List<DictionaryVo> findListByVo(DictionaryVo dictionaryVo) {
        List<DictionaryVo> dictionaryVos = dao.findListByVo(dictionaryVo);
        for (DictionaryVo dictionary : dictionaryVos) {
            if (dictionary.getDicPid() == 0) {
                dictionary.setParent("#");
                dictionary.setIcon("fa fa-home");
            } else {
                dictionary.setParent(dictionary.getDicPid().toString());
                dictionary.setIcon("glyphicon glyphicon-tint");
            }
        }
        return dictionaryVos;
    }

    public AjaxStatus DeleteDic(Long id) {
        AjaxStatus status = new AjaxStatus(true);
        //是否为类，以及类下是否有引用
        Dictionary dictionary = dao.get(id);
        if (dictionary != null && dictionary.getDicPid() == 0) {
            dictionary = new Dictionary();
            dictionary.setDicPid(id);
            List<Dictionary> dics = dao.findList(dictionary);
            if (dics.isEmpty()) {
                //删除
                dao.remove(id);
                status.setSuccess(true);
            } else {
                status.setSuccess(false);
                status.setMessage("该类被引用，不可删除");
            }
        } else if (dictionary != null && dictionary.getDicPid() != 0) {
            dao.remove(id);
            status.setSuccess(true);
        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        return status;
    }

}