package org.radf.plat.commons.safe;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Bea</p>
 * @author KentKong
 * @version 1.0
 */



import org.radf.plat.util.exception.SafeException;

public class SecurityUtil {

	private BASE64Encoder myEncoder = new BASE64Encoder();
	private BASE64Decoder myDecoder = new BASE64Decoder();
	private SafeEngine safe = null;
	private String serverCert = null;

	public SecurityUtil() throws SafeException {
		safe = new SafeEngine();
		int i =
			safe.shecaInitEnviroment(
				2,
				"UserKey.key",
				"aa0ab10a",
				1000,
				2,
				"certchain.spc",
				"");
		if (i != 0) {
			throw new SafeException("安全引擎初始化失败:" + SafeException.getDesc(i));
		}

		serverCert = safe.shecaGetSelfCertificate(2, "UserCert.der", "");

	}

	/**
	 *  Considering Chinese,I encode  input string to BASE64, then encrypt the BASE64 string,
	 *  the client side will decode encrypted string , then encode the BASE64 string.
	 * @param origin
	 * @param cert
	 * @return
	 * @throws SafeException
	 */
	public String doEncrypt(String origin, String cert) throws SafeException {
		String base64 = myEncoder.encode(origin.getBytes());
		String encrypted = safe.shecaEnvelope(1, base64, cert);
		if (safe.getErrorCode() != 0) {
			throw new SafeException(
				"加密失败：" + SafeException.getDesc(safe.getErrorCode()));
		}
		return encrypted;
	}

	/**
	 * Client will pass an encrypted BASE64 string, first decrypte the string , then base64-decode
	 * the string.
	 * @param decrypted
	 * @return
	 * @throws SafeException
	 */
	public String doDecrypt(String encrypted, String cert)
		throws SafeException {
		String decrypted = safe.shecaEnvelope(2, encrypted, cert);
		if (safe.getErrorCode() != 0) {
			throw new SafeException(
				"解密失败：" + SafeException.getDesc(safe.getErrorCode()));
		}
		String decode = "";
		try {
			decode = new String(myDecoder.decodeBuffer(decrypted));
		} catch (Exception ex) {
			throw new SafeException("BASE64解码失败");
		}

		return decode;
	}

	/**
	 * Considering the Chinese, I encode the origin string to BASE64, then signature the
	 * Base64, client side will verify the signature with the BASE64 string.
	 * @param origin
	 * @param cert
	 * @return
	 * @throws Exception
	 */
	public String doSignature(String origin) throws SafeException {
		String base64 = myEncoder.encode(origin.getBytes());
		System.out.println("\n\n base64 is {" + base64 + "}");

		String signed = safe.shecaSignData(base64, 3);
		if (safe.getErrorCode() != 0) {
			throw new SafeException(
				"签名失败：" + SafeException.getDesc(safe.getErrorCode()));
		}
		return signed;

	}

	/**
	 * Client signs the origin string 's BASE64 format, so when verifying , the method
	 * will convert the origin string to BASE64, then verify.
	 * @param origin
	 * @param signed
	 * @param cert
	 * @return
	 * @throws SafeException
	 */
	public boolean doValidate(String origin, String signed, String cert)
		throws SafeException {
		String base64 = myEncoder.encode(origin.getBytes());

		int i = safe.shecaVerifySignData(base64, 3, signed, cert);

		if (i == 0) {
			return true;
		} else {
			throw new SafeException("数字签名验证失败");
		}

	}

	/**
	 * 获得证书
	 * @param name
	 * @return
	 * @throws SafeException
	 */

	public String getCert(String name) throws SafeException {
		String cert = null;

		cert = safe.shecaGetSelfCertificate(2, name, "");
		if (safe.getErrorCode() != 0) {
			throw new SafeException(
				"取得证书失败：" + SafeException.getDesc(safe.getErrorCode()));
		}
		safe.shecaSetConfiguration(0);

		safe.shecaVerifyCertificate(cert);
		if (safe.getErrorCode() != 0) {
			throw new SafeException(
				"验证证书失败：" + SafeException.getDesc(safe.getErrorCode()));
		}

		return cert;
	}

}