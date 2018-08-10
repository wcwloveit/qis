package com.qis.common.util;

import java.io.UnsupportedEncodingException;


public class BytesUtils {

	public static byte[] toBytes(String str, String charSetName) {
		if (str != null) {
			try {
				return str.getBytes(charSetName);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("input string must not be null");
		}
	}

	public static byte[] toBytes(String str) {
		return toBytes(str, "UTF-8");
	}

	public static byte[] toBytes(StringBuilder sb, String charSetName) {
		return toBytes(sb.toString(), charSetName);
	}

	public static byte[] toBytes(StringBuilder sb) {
		return toBytes(sb, "UTF-8");
	}
}
