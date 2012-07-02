package com.phillit.pez.common.util.encrypt;

import org.junit.Test;

public class EncryptTest {

	@Test
	public void t1() throws Exception {
		PezEncrypt.setUp();
		byte[] encryptionBytes = null;
		String input = "ABC123가나다";
		System.out.println("Entered: " + input);

		encryptionBytes = PezEncrypt.encrypt(input);
		String encodeString = PezBASE64Coder.encodeLines(encryptionBytes);

		System.out.println("Base64 Encode : " + encodeString);
		System.out.println("Recovered: "
				+ PezEncrypt.decrypt(PezBASE64Coder.decodeLines(encodeString)));
	}

}
