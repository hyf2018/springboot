package com.haodao.meter.data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.haodao.meter.config.Constant;
import com.haodao.meter.util.BCDUtil;
import com.haodao.meter.util.HexUtil;

/**
 * <实时时间> 比如：“57 83 46 39 3A 53 53”； 原始数据为十六进制，7字节数据； 需要减33H； 解析使用BCD码(8421)算法；
 * 时间格式为：yyyyMMddHHmmss ；
 * 
 * @author hyf
 *
 */
public class CurrentTime {
	/**
	 * 原始数据字符串长度
	 */
	private static final int DATA_LENGTH = 20;

	/**
	 * 原始数据字符串
	 */
	private String data;

	/**
	 * 分隔符，默认空格
	 */
	private String delimiter = " ";

	/**
	 * 数据是否为正序，默认否
	 */
	private boolean isSeqPositive = false;

	/**
	 * 计算实时时间值
	 * 
	 * @return
	 * @throws Exception
	 */
	public LocalDateTime calculate() throws Exception {
		LocalDateTime result = null;
		StringBuilder dataStr = new StringBuilder();

		if (data == null || data.length() != DATA_LENGTH) {
			throw new Exception("数据长度必须为" + DATA_LENGTH);
		}
		String[] arr = data.split(delimiter);
		if (arr.length != 7) {
			throw new Exception("数据格式错误：" + data);
		}
		// 数据值需要减固定值
		// 1.arr[0]
		int data0 = Math.subtractExact(Integer.valueOf(arr[0], 16), Constant.DATA_SUB);
		if (data0 < 0) {
			throw new Exception("数据值错误：" + arr[0]);
		}
		String data0str = Integer.toHexString(data0);
		data0str = HexUtil.fixPrefixZero(data0str, 2);

		// 2.arr[1]
		int data1 = Math.subtractExact(Integer.valueOf(arr[1], 16), Constant.DATA_SUB);
		if (data1 < 0) {
			throw new Exception("数据值错误：" + arr[1]);
		}
		String data1str = Integer.toHexString(data1);
		data1str = HexUtil.fixPrefixZero(data1str, 2);

		// 3.arr[2]
		int data2 = Math.subtractExact(Integer.valueOf(arr[2], 16), Constant.DATA_SUB);
		if (data2 < 0) {
			throw new Exception("数据值错误：" + arr[2]);
		}
		String data2str = Integer.toHexString(data2);
		data2str = HexUtil.fixPrefixZero(data2str, 2);

		// 4.arr[3]
		int data3 = Math.subtractExact(Integer.valueOf(arr[3], 16), Constant.DATA_SUB);
		if (data3 < 0) {
			throw new Exception("数据值错误：" + arr[3]);
		}
		String data3str = Integer.toHexString(data3);
		data3str = HexUtil.fixPrefixZero(data3str, 2);

		// 5.arr[4]
		int data4 = Math.subtractExact(Integer.valueOf(arr[4], 16), Constant.DATA_SUB);
		if (data4 < 0) {
			throw new Exception("数据值错误：" + arr[4]);
		}
		String data4str = Integer.toHexString(data4);
		data4str = HexUtil.fixPrefixZero(data4str, 2);

		// 6.arr[5]
		int data5 = Math.subtractExact(Integer.valueOf(arr[5], 16), Constant.DATA_SUB);
		if (data5 < 0) {
			throw new Exception("数据值错误：" + arr[5]);
		}
		String data5str = Integer.toHexString(data5);
		data5str = HexUtil.fixPrefixZero(data5str, 2);

		// 7.arr[6]
		int data6 = Math.subtractExact(Integer.valueOf(arr[6], 16), Constant.DATA_SUB);
		if (data6 < 0) {
			throw new Exception("数据值错误：" + arr[6]);
		}
		String data6str = Integer.toHexString(data6);
		data6str = HexUtil.fixPrefixZero(data6str, 2);

		if (isSeqPositive) {
			dataStr = dataStr.append(data0str).append(data1str).append(data2str).append(data3str).append(data4str)
					.append(data5str).append(data6str);
		} else {
			// 数据值为倒序
			dataStr = dataStr.append(data6str).append(data5str).append(data4str).append(data3str).append(data2str)
					.append(data1str).append(data0str);
		}

		// BCD码格式检查
		if (!BCDUtil.checkByHex(dataStr.toString())) {
			throw new Exception("数据值BCD码格式错误：" + dataStr);
		}
		System.out.println("实时时间BCD码:" + dataStr);

		// 时间格式化,格式为：yyyyMMddHHmmss
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		result = LocalDateTime.parse(dataStr, formatter);

		return result;
	}

	public CurrentTime(String data) {
		super();
		this.data = data;
	}

	public CurrentTime(String data, String delimiter, boolean isSeqPositive) {
		super();
		this.data = data;
		this.delimiter = delimiter;
		this.isSeqPositive = isSeqPositive;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public boolean isSeqPositive() {
		return isSeqPositive;
	}

	public void setSeqPositive(boolean isSeqPositive) {
		this.isSeqPositive = isSeqPositive;
	}

	public static void main(String[] args) throws Exception {
		String data = "57 83 46 39 3A 53 53";
		CurrentTime cn = new CurrentTime(data);
		LocalDateTime time = cn.calculate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String str = formatter.format(time);
		System.out.println(str);
	}

}
