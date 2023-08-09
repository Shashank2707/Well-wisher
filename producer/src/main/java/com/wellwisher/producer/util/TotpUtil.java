package com.wellwisher.producer.util;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class TotpUtil {

	public static boolean validate(String secretKey, String otp) {
		Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey.toUpperCase());
	    String hexKey = Hex.encodeHexString(bytes);
		return TOTP.validate(hexKey, otp);
	}
}
