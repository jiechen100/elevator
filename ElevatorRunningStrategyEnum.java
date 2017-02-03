package com.jie.elevator;

public enum ElevatorRunningStrategyEnum {
	A((short) 0, "A"), B((short) 1, "B");

	private final short value;
	private final String description;

	ElevatorRunningStrategyEnum(final short value, String desc) {
		this.value = value;
		this.description = desc;
	}

	public short value() {
		return value;
	}

	public String description() {
		return description;
	}

	public static ElevatorRunningStrategyEnum get(final String description) {
		switch (description) {
		case "A":
			return A;
		case "B":
			return B;
		default:
			return null;
		}
	}
}
