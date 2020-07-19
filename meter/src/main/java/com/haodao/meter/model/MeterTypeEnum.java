package com.haodao.meter.model;

import java.util.Arrays;

/**
 * 表类型T（type）：表示表的类型。其值： T=011H：冷水水表, T=012H：纯净水水表 T=018H：工业大表 T=021H：热量表
 * T=031H：煤气表 T=041H：电表 T=051~099H：备用
 * 
 * @author hyf
 *
 */
public enum MeterTypeEnum {
	COLDWATER(0x11, "冷水水表"), PURE(0x12, "纯净水水表"), INDUSTRY(0x18, "工业大表"), HEAT(0x21, "热量表"), GAS(0x31, "煤气表"),
	ELECTRIC(0x41, "电表");

	private int code;
	private String name;

	private MeterTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static MeterTypeEnum getByCode(int code) {
		return Arrays.stream(MeterTypeEnum.values()).filter(t -> t.getCode() == code).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return getCode() + "_" + getName();
	}

	public static void main(String[] args) {
		int code = 0x11;
		MeterTypeEnum mt = MeterTypeEnum.getByCode(code);
		System.out.println(mt);
	}

}
