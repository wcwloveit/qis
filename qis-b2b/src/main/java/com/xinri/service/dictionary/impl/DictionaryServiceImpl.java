package com.xinri.service.dictionary.impl;
import org.springframework.stereotype.Service;
import com.qis.common.service.CrudService;
import com.xinri.po.dictionary.Dictionary;
import com.xinri.dao.dictionary.DictionaryMapper;
import com.xinri.service.dictionary.IDictionaryService;
/**
 * <p></p>
 * 类名:DictionaryServiceImpl<br>
 * 创建人:xiashanyong<br>
 * 创建时间:20180813<br>
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends CrudService<DictionaryMapper,Dictionary>  implements IDictionaryService{


}