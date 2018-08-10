package com.qis.common.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	
	private static final Logger logger = Logger.getLogger(SerializeUtil.class);

	
      public static byte[] serialize(Object object) throws Exception {
           ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                 byte[] bytes = baos.toByteArray();
                 return bytes;
           } catch (Exception e) {
        	   logger.info("缓存序列化异常" + e.toString());
        	   throw e;
           }
     }

      public static Object unserialize( byte[] bytes) throws Exception {
           ByteArrayInputStream bais = null;
            try {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                 return ois.readObject();
           } catch (Exception e) {
        	   logger.info("缓存序列化异常" + e.toString());
        	   throw e;
           }
     }
}