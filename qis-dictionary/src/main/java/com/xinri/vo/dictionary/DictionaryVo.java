package com.xinri.vo.dictionary;

import com.xinri.po.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryVo extends Dictionary {

    public String icon;

    private String text;

    private String parent;

    public List<DictionaryVo > dictionaryVos = new ArrayList<DictionaryVo>();

    public List<DictionaryVo> getDictionaryVos() {
        return dictionaryVos;
    }

    public void setDictionaryVos(List<DictionaryVo> dictionaryVos) {
        this.dictionaryVos = dictionaryVos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
