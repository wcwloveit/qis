package com.xinri.service.dictionary.impl;

import com.qis.common.service.CrudService;
import com.xinri.dao.dictionary.DictionaryMapper;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.service.dictionary.IDictionaryService;
import com.xinri.util.AjaxStatus;
import com.xinri.vo.dictionary.DictionaryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建人:汪震
 * 创建时间:20180813
 */
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

    /**
     * @param dicKey
     * @return
     * @description 通过dicKey获取数据
     */
    @Override
    public List<Dictionary> reads(String dicKey) {
        Dictionary dictionary = new Dictionary();
        dictionary.setDicKey(dicKey);
        Dictionary dc = dao.getByEntity(dictionary);
        dictionary = new Dictionary();
        dictionary.setDicPid(dc.getId());
        List<Dictionary> dcList = dao.findList(dictionary);
        return dcList;
    }

    /**
     * 查询指定数据值
     *
     * @param dicKey
     * @param subKey
     * @return
     */
    public String getDicValue(String dicKey, String subKey) {
        Dictionary dictionary = new Dictionary();
        dictionary.setDicKey(dicKey);
        Dictionary dc = dao.getByEntity(dictionary);
        dictionary = new Dictionary();
        dictionary.setDicPid(dc.getId());
        dictionary.setDicKey(subKey);
        dictionary = dao.getByEntity(dictionary);
        return dictionary.getDicValue();
    }

    /**
     * 查询指定数据描述
     * @param dicKey
     * @param subKey
     * @retur
     */
//    public String getRemark(String dicKey,String subKey){
//        Dictionary dictionary = new Dictionary();
//        dictionary.setDicKey(dicKey);
//        Dictionary dc = dao.getByEntity(dictionary);
//        dictionary = new Dictionary();
//        dictionary.setDicPid(dc.getId());
//        dictionary.setDicKey(subKey);
//        dictionary=dao.getByEntity(dictionary);
//        return dictionary.getRemark();
//    }

    /**
     * 查询指定数据值
     *
     * @param dicKey
     * @param subValue
     * @return
     */
    @Override
    public String getDicKey(String dicKey, String subValue) {
        Dictionary dictionary = new Dictionary();
        dictionary.setDicKey(dicKey);
        Dictionary dc = dao.getByEntity(dictionary);
        dictionary = new Dictionary();
        dictionary.setDicPid(dc.getId());
        dictionary.setDicValue(subValue);
        dictionary = dao.getByEntity(dictionary);
        if (dictionary == null) {
            return "";
        } else {
            return dictionary.getDicKey();
        }
    }

    @Transactional
    @Override
    public void batchSaveDictionary(List<Dictionary> list) {
        dao.batchInsert(list);
    }
}