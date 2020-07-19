package com.haodao.meter.config;

public class Constant {

	/**
	 * 唤醒前导字节
	 */
	public static int PREFIX = 0xFE;

	/**
	 * 帧起始符
	 */
	public static int START = 0x68;

	/**
	 * 帧结束符
	 */
	public static int END = 0x16;

	/**
	 * 数据域的减数，33H
	 */
	public static int DATA_SUB = 0x33;
}
