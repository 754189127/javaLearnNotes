package notes;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.poi.hpsf.Util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {
	
	private static String DES_ALGORITHM = "DES";
	/**
	 * 根据参数生成 KEY
	 */
	private static Key getKey(String strKey) {
		try {
			// KeyGenerator _generator = KeyGenerator.getInstance("DES");
			// _generator.init(new SecureRandom(strKey.getBytes()));
			// key = _generator.generateKey();
			// _generator = null;

			SecureRandom _secureRandom = SecureRandom.getInstance("SHA1PRNG");
			_secureRandom.setSeed(strKey.getBytes());
			KeyGenerator kg = null;
			kg = KeyGenerator.getInstance(DES_ALGORITHM);
			kg.init(_secureRandom);
			// kg.init(56, secureRandom);

			// 生成密钥
			return kg.generateKey();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		}
		// return key;
	}
	
	/*
	private SecretKey getKey(String secretKey)
			throws NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(DES_ALGORITHM);
		DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
		keyFactory.generateSecret(keySpec);
		return keyFactory.generateSecret(keySpec);
	}*/

	/**
	 * 加密 String 明文输入 ,String 密文输出
	 */
	public static String encode(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF-8");
			byteMi = Encrypt.encryptByte(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 �?String 密文输入 ,String 明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String decode(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
//		System.out.println(strMi.getBytes().length);
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = Encrypt.decryptByte(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密�?byte[] 明文输入 ,byte[] 密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	public static byte[] encryptByte(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			//cipher.init(Cipher.ENCRYPT_MODE, getKey(ENCRYPT_KEY));
			//cipher.init(Cipher.ENCRYPT_MODE, getKey(Util.getProperties("ENCRYPT_KEY")));
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密�?byte[] 密文输入 , �?byte[] 明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	public static byte[] decryptByte(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			//cipher.init(Cipher.DECRYPT_MODE, getKey(Util.getProperties("ENCRYPT_KEY")));
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
//		System.out.println(byteFina.length);
		return byteFina;
	}

	
	public static void main(String[] args) {
		String strMing = "<?xml version="+"1.0"+" encoding="+"utf-8"+"standalone="+"yes"+"?><request><SystemInfo><SystemName>FJXXT</SystemName><Version>1.0</Version><RequestInfo><CpCode>FJXXTCZT</CpCode><SaleModalId>3907042000</SaleModalId><SerialId>1618862005411684</SerialId><RequestTime>2016-11-04 15:55:35</RequestTime></RequestInfo></SystemInfo><ServiceInfo><ServiceName>007</ServiceName><Smsinfo><SchoolId>1</SchoolId><IsLong>1</IsLong><FamilyId>3407</FamilyId><Content>test message!</Content></Smsinfo></ServiceInfo></request>";
		Encrypt.encode(strMing);
		String test = "abcd1234";
		String s = "nh5os9KdYYloLDEAAjknMc1mobYofaepkpO8VmQOLmProkitBJHeH8RN0yloaXZ6fU8DxSA6kKL1h9sAqYjd6xFtvOTbSh+8R+UKh/RuXr91MzKZMakoYC/eLhqaDB9zs0DAbtVoIQKj/ZF8opdwCcBbXYX2L32MuAIzrsMwe8pe3Tw6fAatiUZ6kZ5nCj8a";
//		
		//System.out.print("加密前："+test+",加密后："+Encrypt.encode(test));
		System.out.println("sssssssssssssss"); 
	}
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main2(String[] args) throws Exception {
		//String strMing = "<?xml version="+"1.0"+" encoding="+"utf-8"+"standalone="+"yes"+"?><request><SystemInfo><SystemName>FJXXT</SystemName><Version>1.0</Version><RequestInfo><CpCode>FJXXTCZT</CpCode><SaleModalId>3907042000</SaleModalId><SerialId>1618862005411684</SerialId><RequestTime>2016-11-04 15:55:35</RequestTime></RequestInfo></SystemInfo><ServiceInfo><ServiceName>007</ServiceName><Smsinfo><SchoolId>1</SchoolId><IsLong>1</IsLong><FamilyId>3407</FamilyId><Content>test message!</Content></Smsinfo></ServiceInfo></request>";
		//Encrypt.encode(strMing);
//		System.out.println(Encrypt.encode("1234"));
//		System.out.println(Encrypt.encode(strMing));
		String test = "abcd1234";
		String s = "nh5os9KdYYloLDEAAjknMc1mobYofaepkpO8VmQOLmProkitBJHeH8RN0yloaXZ6fU8DxSA6kKL1h9sAqYjd6xFtvOTbSh+8R+UKh/RuXr91MzKZMakoYC/eLhqaDB9zs0DAbtVoIQKj/ZF8opdwCcBbXYX2L32MuAIzrsMwe8pe3Tw6fAatiUZ6kZ5nCj8a";
//		System.out.println(Encrypt.decode("nh5os9KdYYloLDEAAjknMc1mobYofaepkpO8VmQOLmProkitBJHeH8RN0yloaXZ6fU8DxSA6kKL1h9sAqYjd6xFtvOTbSh+8R+UKh/RuXr91MzKZMakoYC/eLhqaDB9zs0DAbtVoIQKj/ZF8opdwCcBbXYX2L32MuAIzrsMwe8pe3Tw6fAatiUZ6kZ5nCj8a"));
		System.out.println("加密前："+test+",加密后："+Encrypt.encode(test));
	}
}
