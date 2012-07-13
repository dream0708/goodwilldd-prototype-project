package com.phillit.prototype.pez.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PezUtil {
	private static final Logger log = LoggerFactory.getLogger(PezUtil.class);

	private PezUtil() {
	}

	/**
	 * 현재 HttpServletRequest를 반환한다.
	 * 
	 * @return
	 */
	public static HttpServletRequest getCurrentRequest() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return sra.getRequest();
	}

	/**
	 * 비밀번호를 MD5로 변환하여 반환한다.
	 * 
	 * @param password
	 * @return
	 */
	public static String pezMD5Encript(String password) {
		MessageDigest md;
		String md5Passwd = null;

		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] raw = md.digest();
			md5Passwd = new String(Base64.encode(raw));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			md5Passwd = null;
		}

		return md5Passwd;
	}

	/**
	 * MD5로 암호화 된 패스워드를 비교하여 일치여부를 반환한다.
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public static boolean pezPasswordMatch(String oldPassword,
			String newPassword) {
		return MessageDigest.isEqual(oldPassword.getBytes(),
				newPassword.getBytes());
	}
}
