package com.haodao.meter.model;

import java.util.Arrays;

/**
 * <错误状态 > D0 :阀门状态 0=开 1=关 ; D1 :主电池当前电压 0=正常 1=欠压 ; D3 :防拆开关 0=未拆 1=拆开 ; D4
 * :备用电池当前电压 0=正常 1=欠压 ; D7 :反转报警状态 0=正常 1=故障 ;
 * 
 * @author hyf
 *
 */
public enum ErrorStatusEnum {
	// D0_0、D0_1
	D0_0("D0_0", "阀门状态", "开"), D0_1("D0_1", "阀门状态", "关"), D1_0("D1_0", "主电池当前电压", "正常"), D1_1("D1_1", "主电池当前电压", "欠压"),
	D3_0("D3_0", "防拆开关", "未拆"), D3_1("D3_1", "防拆开关", "拆开"), D4_0("D4_0", "备用电池当前电压", "正常"),
	D4_1("D4_1", "备用电池当前电压", "欠压"), D7_0("D7_0", "反转报警状态", "正常"), D7_1("D7_1", "反转报警状态", "故障");

	private String code;
	private String name;
	// 结果说明
	private String result;

	private ErrorStatusEnum(String code, String name, String result) {
		this.code = code;
		this.name = name;
		this.result = result;
	}

	public static ErrorStatusEnum getByCode(String code) {
		return Arrays.stream(ErrorStatusEnum.values()).filter(t -> t.getCode().equals(code)).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return getName() + getResult();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public static void main(String[] args) {
		String code = "D0_1";
		ErrorStatusEnum mt = ErrorStatusEnum.getByCode(code);
		System.out.println(mt);
	}

}
