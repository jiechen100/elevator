package com.jie.elevator;

public enum ControlCommandEnum {
	MOVE((short) 1, "Move"), STOP((short) 2, "Stop");

	private final short value;
	private final String description;

	ControlCommandEnum(final short value, String desc) {
		this.value = value;
		this.description = desc;
	}

	public short value() {
		return value;
	}

	public String description() {
		return description;
	}
}
