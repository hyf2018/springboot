package com.haodao.meter.data;

import java.math.BigDecimal;

import com.haodao.meter.config.Constant;
import com.haodao.meter.model.UOMEnum;
import com.haodao.meter.util.BCDUtil;
import com.haodao.meter.util.HexUtil;

/**
 * <累计反向累积流量> 比如：“36 35 34 33 58”； 原始数据为十六进制，4字节数据，1字节单位； 需要减33H； 解析使用BCD码(8421)算法；
 * 
 * @author hyf
 *
 */
public class ReverseTotal {
	/**
	 * 原始数据字符串长度
	 */
	private static final int DATA_LENGTH = 14;

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
	 * 计算累计反向累积流量值
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object[] calculate() throws Exception {
		Object[] result = new Object[2];
		BigDecimal dataValue = new BigDecimal("0");
		StringBuilder dataStr = new StringBuilder();

		if (data == null || data.length() != DATA_LENGTH) {
			throw new Exception("数据长度必须为" + DATA_LENGTH);
		}
		String[] arr = data.split(delimiter);
		if (arr.length != 5) {
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

		if (isSeqPositive) {
			dataStr = dataStr.append(data0str).append(data1str).append(data2str).append(data3str);
		} else {
			// 数据值为倒序
			dataStr = dataStr.append(data3str).append(data2str).append(data1str).append(data0str);
		}

		// BCD码格式检查
		if (!BCDUtil.checkByHex(dataStr.toString())) {
			throw new Exception("数据值BCD码格式错误：" + dataStr);
		}
		System.out.println("累计反向累积流量BCD码:" + dataStr);

		// 单位
		// 数据值需要减固定值
		Integer uomCode = Math.subtractExact(Integer.valueOf(arr[4], 16), Constant.DATA_SUB);
		UOMEnum uom = UOMEnum.getByCode(uomCode);
		if (uom == null) {
			throw new Exception("单位错误：" + arr[4]);
		}

		Integer dataVal = Integer.valueOf(dataStr.toString(), 10);
		if (dataVal != 0) {
			dataValue = new BigDecimal(dataVal + "").divide(new BigDecimal(uom.getDivisor() + ""), 10,
					BigDecimal.ROUND_HALF_UP);
		}

		result[0] = dataValue;
		result[1] = uom.getUom();

		return result;
	}

	public ReverseTotal(String data) {
		super();
		this.data = data;
	}

	public ReverseTotal(String data, String delimiter, boolean isSeqPositive) {
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
		String data = "36 35 34 33 58";
		ReverseTotal cn = new ReverseTotal(data);
		Object[] obj = cn.calculate();
		System.out.println(obj[0]);
		System.out.println(obj[1]);
	}

}
