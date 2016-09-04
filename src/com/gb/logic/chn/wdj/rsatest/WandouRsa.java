package com.gb.logic.chn.wdj.rsatest;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA工具类
 */
public class WandouRsa {
	// 豌豆荚公钥
	public final static String WandouPublicKey = "e66eda02d2be81c5b63a535b76bd7cbc";

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	public static boolean doCheck(String content, String sign) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(WandouPublicKey);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
