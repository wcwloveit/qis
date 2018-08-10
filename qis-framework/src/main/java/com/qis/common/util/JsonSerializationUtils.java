/**
 * 
 * 替换特殊字符如(大小尖括号，回车符等)
 * @author Hui
 * @version
 */
package com.qis.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class JsonSerializationUtils extends JsonSerializer<String>{

	@Override
	public void serialize(String value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		if(value.isEmpty()){
			jgen.writeString("");
		}
		if(value.contains("<")){//小于
			value = value.replaceAll("<", "&lt;");
		}
		if(value.contains(">")){//大于
			value = value.replaceAll(">", "&gt;");
		}
		if(value.contains("\r\n")){//回车
			value = value.replaceAll("\r\n", "<br/>");
		}
		if(value.contains("\n")){//换行
			value = value.replaceAll("\n", "<br/>");
		}
		jgen.writeString(value);
	}
}
