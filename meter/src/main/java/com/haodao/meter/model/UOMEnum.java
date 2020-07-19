package com.haodao.meter.model;

import java.util.Arrays;

/**
 * 单位 <1.吨：累计 Total> 21H ×.×××××××吨 ； 22H ××.××××××吨 ； 23H ×××.×××××吨 ； 24H
 * ××××.××××吨 ； 25H ×××××.×××吨 ； 26H ××××××.××吨 ； 27H ×××××××.×吨 ； 28H ××××××××
 * 吨 ；
 * 
 * <2.元：金额 Amt> 44H ××.××元 ； 42H ××.××元 ； 41H ××.×××元 ； 40H ××.××××元 ；
 * 
 * <3.m3/h:流量 Flow> 01H ×× m3/h ； 0AH ××.× m3/h ； 64H ××.×× m3/h ；
 * 
 * 
 * 
 * @author hyf
 *
 */
public enum UOMEnum {
	// <1.吨：>
	T21H(0x21, "吨", 10000000), T22H(0x22, "吨", 1000000), T23H(0x23, "吨", 100000), T24H(0x24, "吨", 10000),
	T25H(0x25, "吨", 1000), T26H(0x26, "吨", 100), T27H(0x27, "吨", 10), T28H(0x28, "吨", 1),
	// <2.元：>
	A44H(0x44, "元", 100), A42H(0x42, "元", 100), A41H(0x41, "元", 1000), A40H(0x40, "元", 10000),
	// <3.m3/h:>
	F01H(0x01, "m3/h", 1), F0AH(0x0A, "m3/h", 10), F64H(0x64, "m3/h", 100);

	private int code;
	private String uom;
	// 倍率，以除数表示
	private int divisor;

	private UOMEnum(int code, String uom, int divisor) {
		this.code = code;
		this.uom = uom;
		this.divisor = divisor;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getDivisor() {
		return divisor;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public static UOMEnum getByCode(int code) {
		return Arrays.stream(UOMEnum.values()).filter(t -> t.getCode() == code).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return getCode() + "_" + getUom() + "_" + getDivisor() + "(除数)";
	}

	public static void main(String[] args) {
		int code = 0xFF;
		UOMEnum mt = UOMEnum.getByCode(code);
		System.out.println(mt);
	}

}
