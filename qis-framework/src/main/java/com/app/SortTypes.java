package com.app;

import com.google.common.collect.Maps;

import java.util.Map;

public class SortTypes{
    private static Map<String,String> sortTypes=Maps.newLinkedHashMap();
    static{
        sortTypes.put("auto","自动");
        sortTypes.put("title","名称");
    }
    public static Map<String,String> Get(){
        return sortTypes;
    }
}
