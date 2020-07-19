package com.haodao.meter.util;

/**
 * <BCD码> 8421编码：它和四位自然二进制码相似，各位的权值为8、4、2、1，故称为有权BCD码 ；
 * 只选用了0000~1001分别代表它所对应的十进制数0~9 ；
 * 
 * @author hyf
 *
 */
public class BCDUtil {
	/**
	 * 验证16进制字符是否符合BCD编码格式
	 * 
	 * @param hexStr
	 * @return
	 * @throws Exception
	 */
	public static boolean checkByHex(String hexStr) throws Exception {
		int max = 9;
		if (hexStr == null) {
			throw new Exception("null hexStr");
		}
		String[] strs = hexStr.split("");
		for (String str : strs) {
			Integer value = Integer.valueOf(str, 16);
			if (value > max) {
				return false;
			}
		}

		return true;
	}
	
	public static void main(String[] args) throws Exception {
		String hexStr = "1";
		boolean b = checkByHex(hexStr);
		System.out.println(b);
	}
}
