package org.radf.plat.commons.safe;

public class SafeEngine {

	public SafeEngine() {
		intHSE = -3;
		intReturned = -1;
		intDllHandle = -1;
	}

	public int getCode() {
		return intHSE;
	}

	public int getErrorCode() {
		return intReturned;
	}

	String native2unicode(String s) {
		if (s == null || s.length() == 0)
			return "";
		byte abyte0[] = new byte[s.length()];
		for (int i = 0; i < s.length(); i++)
			abyte0[i] = (byte) s.charAt(i);

		return new String(abyte0);
	}

	public native int shecaChangePassword(
		int i,
		String s,
		String s1,
		String s2);

	public native int shecaChangePasswordByte(
		int i,
		byte abyte0[],
		byte abyte1[],
		byte abyte2[]);

	public native int shecaClearEnviroment();

	public native String shecaDigest(String s, int i);

	public native byte[] shecaDigestByte(byte abyte0[], int i);

	public native String shecaEnvelope(int i, String s, String s1);

	public native byte[] shecaEnvelopeByte(int i, byte abyte0[], byte abyte1[]);

	public native long shecaGetCertClass(String s);

	public native long shecaGetCertClassByte(byte abyte0[]);

	public String shecaGetCertDetail(String s, int i) {
		String s1 = shecaGetCertDetail1(s, i);
		return native2unicode(s1);
	}

	public native String shecaGetCertDetail1(String s, int i);

	public native byte[] shecaGetCertDetailByte(byte abyte0[], int i);

	public native String shecaGetCertInfoByOID(String s, String s1);

	public native byte[] shecaGetCertInfoByOIDByte(
		byte abyte0[],
		byte abyte1[]);

	public native String shecaGetCertUniqueID(String s);

	public native byte[] shecaGetCertUniqueIDByte(byte abyte0[]);

	public native long shecaGetCertUsage(String s);

	public native long shecaGetCertUsageByte(byte abyte0[]);

	public native long shecaGetCertValidDate(String s);

	public native long shecaGetCertValidDateByte(byte abyte0[]);

	public native String shecaGetCertificate(String s, String s1);

	public native byte[] shecaGetCertificateByte(byte abyte0[], byte abyte1[]);

	public native long shecaGetModuleNo();

	public native String shecaGetSelfCertificate(int i, String s, String s1);

	public native byte[] shecaGetSelfCertificateByte(
		int i,
		byte abyte0[],
		byte abyte1[]);

	public native long shecaGetVersion();

	public native int shecaInitEnviroment(
		int i,
		String s,
		String s1,
		int j,
		int k,
		String s2,
		String s3);

	public native int shecaInitEnviromentByte(
		int i,
		byte abyte0[],
		byte abyte1[],
		int j,
		int k,
		byte abyte2[],
		byte abyte3[]);

	public native byte[] shecaPEMDecode(byte abyte0[]);

	public native byte[] shecaPEMEncode(byte abyte0[]);

	public native int shecaSetConfiguration(int i);

	public native int shecaShowVersion();

	public native String shecaSignData(String s, int i);

	public native byte[] shecaSignDataByte(byte abyte0[], int i);

	public native int shecaVerifyCertificate(String s);

	public native int shecaVerifyCertificateByte(byte abyte0[]);

	public native int shecaVerifyCertificateOnline(String s);

	public native int shecaVerifyCertificateOnlineByte(byte abyte0[]);

	public native int shecaVerifySignData(
		String s,
		int i,
		String s1,
		String s2);

	public native int shecaVerifySignDataByte(
		byte abyte0[],
		int i,
		byte abyte1[],
		byte abyte2[]);

	int intHSE;
	int intReturned;
	int intDllHandle;

	static {
		try {
			System.loadLibrary("javasafeengine");
		} catch (Exception _ex) {
			System.out.println("Load dll error");
		}
	}
}