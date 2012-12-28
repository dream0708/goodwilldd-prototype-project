/*
 * Copyright (c) 2009 UZEN
 * All right reserved.
 *
 * This software is the proprietary information of UZEN
 *
 * Revision History
 * Author			Date				Description
 * -------------	--------------		------------------
 * Kwonki Yoon		2009. 10. 15.		First Draft.
 * Jungil Kong      2010.  3. 30.       이름 변경
 */
package com.cj.mobile.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

	/**
	 * 입력 받은 바이트 데이터 SHA256으로 변환한다.
	 * 
	 * @param inData
	 *            String 평문
	 * @param szKey
	 *            byte[] key
	 * @return byte[] 암호화 된 데이터
	 */
	public static byte[] digestSHA256(byte[] sbuffer) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			return digest.digest(sbuffer);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 입력 받은 바이트 데이터 SHA256으로 변환한다.
	 * 
	 * @param inData
	 *            String 평문
	 * @param szKey
	 *            byte[] key
	 * @return byte[] 암호화 된 데이터
	 */
	public static String digestSHA256(String data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			byte[] bin = digest.digest(data.getBytes("ISO8859-1"));
			String mdData = "";
			for (byte b : bin) {
				String t = Integer.toHexString(b);
				if (t.length() < 2) {
					mdData += "0" + t;
				} else {
					mdData += t.substring(t.length() - 2);
				}
			}
			return mdData;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
