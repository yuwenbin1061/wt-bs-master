package wt.bs.utils;

import java.security.MessageDigest;

public class MD5Utils {
	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final char MD_5_STRING[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * @param body body
	 * @return String
	 */
	public static String md5(String body) {
		return md5(body, DEFAULT_CHARSET);
	}

	/**
	 * @param body body
	 * @param charset charset
	 * @return String
	 */
	private static String md5(String body, String charset) {
		try {
			byte[] btInput = body.getBytes(charset);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = MD_5_STRING[byte0 >>> 4 & 0xf];
				str[k++] = MD_5_STRING[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
