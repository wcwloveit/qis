package com.app.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataJsonMapper extends ObjectMapper{
	public DataJsonMapper(){
        super();
        //
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        //
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
       /*
        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,true);
        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS,true);*/
        //
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object value,JsonGenerator jg,SerializerProvider sp) throws IOException, JsonProcessingException{
                jg.writeString("");
            }
        });
    }

}
