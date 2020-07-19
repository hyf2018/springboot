package com.haodao.meter.data;

import java.util.ArrayList;
import java.util.List;

import com.haodao.meter.model.ErrorStatusEnum;
import com.haodao.meter.util.HexUtil;

/**
 * <错误状态> 比如：“3B 33”； 第二字节表示错误代码；
 * 
 * @author hyf
 *
 */
public class ErrorStatus {
	/**
	 * 原始数据字符串长度
	 */
	private static final int DATA_LENGTH = 5;

	/**
	 * 原始数据字符串
	 */
	private String data;

	/**
	 * 分隔符，默认空格
	 */
	private String delimiter = " ";

	/**
	 * 计算当前底数值
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ErrorStatusEnum> calculate() throws Exception {
		List<ErrorStatusEnum> result = new ArrayList<ErrorStatusEnum>();

		if (data == null || data.length() != DATA_LENGTH) {
			throw new Exception("数据长度必须为" + DATA_LENGTH);
		}
		String[] arr = data.split(delimiter);
		if (arr.length != 2) {
			throw new Exception("数据格式错误：" + data);
		}
		// 1.arr[0]

		// 2.第二字节表示错误代码 arr[1]
		String status = HexUtil.hexStr2binaryStr(arr[1]);
		if (status.length() != 8) {
			throw new Exception("status长度错误：" + status);
		}
		String[] staArray = status.split("");
		for (int i = 0; i < staArray.length; i++) {
			String code = "D" + i + "_" + staArray[i];
			ErrorStatusEnum ese = ErrorStatusEnum.getByCode(code);
			if (ese != null) {
				result.add(ese);
			}
		}

		return result;
	}

	public ErrorStatus(String data) {
		super();
		this.data = data;
	}

	public ErrorStatus(String data, String delimiter) {
		super();
		this.data = data;
		this.delimiter = delimiter;
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

	public static void main(String[] args) throws Exception {
		String data = "3B 33";
		ErrorStatus cn = new ErrorStatus(data);
		List<ErrorStatusEnum> list = cn.calculate();
		System.out.println(list);
	}

}
