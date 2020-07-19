package com.haodao.meter.model;

import java.util.Arrays;

/**
 * 〈控制码〉 000000：保留 00h 工控机模拟水表数据 100001: 写表类型 21h 100010: 读存储数据 22h 100011: 读内存参数
 * 23h 100100：读计量数据 24h 100101：读地址 25h 100110：写表号 26h 100111: 读金额和累计流量 27h
 * 101000：广播校时 28h 101001: 写修改时间 29h 101010: 写流量系数 2Ah 101011: 读低区流量系数 2Bh
 * 101100: 读高区流量系数 2Ch .....
 * 
 * 
 * @author hyf
 *
 */
public enum CommandEnum {
	WRITETABLETYPE(0x21, "写表类型"), READSTORE(0x22, "读存储数据"), READMEMORY(0x23, "读内存参数"), READMEASURE(0x24, "读计量数据"),
	READADDRESS(0x25, "读地址 "), WRITETABLENO(0x26, "写表号"), READAMT(0x27, "读金额和累计流量");

	private int code;
	private String name;

	private CommandEnum(int code, String name) {
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

	public static CommandEnum getByCode(int code) {
		return Arrays.stream(CommandEnum.values()).filter(t -> t.getCode() == code).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return getCode() + "_" + getName();
	}

	public static void main(String[] args) {
		int code = 0x27;
		CommandEnum mt = CommandEnum.getByCode(code);
		System.out.println(mt);
	}

}
