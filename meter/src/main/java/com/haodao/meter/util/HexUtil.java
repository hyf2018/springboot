package com.haodao.meter.util;


public class HexUtil {

	/**
	 * 补充16进制前导0
	 * 
	 * @param hexStr
	 * @param maxLength
	 * @return
	 * @throws Exception
	 */
	public static String fixPrefixZero(String hexStr, int maxLength) throws Exception {
		if (hexStr == null) {
			throw new Exception("null hexStr");
		}
		if (hexStr.length() == 0 || hexStr.length() > maxLength) {
			throw new Exception("hexStr长度不能大于" + maxLength + ":" + hexStr);
		}

		// 前面补0
		StringBuffer zero = new StringBuffer("");
		String zeroStr = "0";
		for (int i = 0; i < maxLength - hexStr.length(); i++) {
			zero.append(zeroStr);
		}

		return zero.toString() + hexStr;
	}

	/**
	 * 16进制转2进制字符串
	 * 
	 * @param hexString (注意：最大值0x7fffffffffffffff)
	 * @return
	 */
	public static String hexStr2binaryStr(String hexString) throws Exception {
		String result = "";
		if (hexString == null) {
			throw new Exception("hexString is null");
		}
		if (hexString.length() == 0)
			return result;

		// 在int范围内
		if (hexString.length() > 16
				|| (Integer.valueOf(hexString.substring(0, 1), 16) > 7 && hexString.length() == 16)) {
			throw new Exception("数据不能超过0x7fffffffffffffff:" + hexString);
		}

		String binaryString = Long.toBinaryString(Long.valueOf(hexString, 16));

		// 前面补0
		int len = 4;
		StringBuffer zero = new StringBuffer("");
		String zeroStr = "0";
		if (binaryString != null && binaryString.length() % len > 0) {
			int mod = binaryString.length() % len;
			for (int i = 0; i < len - mod; i++) {
				zero.append(zeroStr);
			}
		}
		result = zero + binaryString;
		if (result.length() < len * hexString.length()) {
			for (int i = 0; i < len * hexString.length() - result.length(); i++) {
				zero.append(zeroStr);
			}
		}
		result = zero + binaryString;

		return result;
	}

	public static void main(String[] args) throws Exception {
//		String hexString = fixPrefixZero("111", 2);
//		System.out.println(hexString);
		
		String str = hexStr2binaryStr("33");
		System.out.println(str);
	}
}
