package com.jie.elevator;

public class Command {
	private ControlCommandEnum controlCommand;
	private int fromFloorNumber;
	private int toFloorNumber;

	public Command(ControlCommandEnum todo) {
		this.controlCommand = todo;
	}

	public Command(int fromFloorNumber, int toFloorNumber) {
		this.controlCommand = ControlCommandEnum.MOVE;
		this.fromFloorNumber = fromFloorNumber;
		this.toFloorNumber = toFloorNumber;
	}

	// public int getFromFloorNumber() {
	// return fromFloorNumber;
	// }

	public int getFromFloorNumber() {
		return fromFloorNumber;
	}

	public void setFromFloorNumber(int fromFloorNumber) {
		this.fromFloorNumber = fromFloorNumber;
	}

	public int getToFloorNumber() {
		return toFloorNumber;
	}

	public void setToFloorNumber(int toFloorNumber) {
		this.toFloorNumber = toFloorNumber;
	}

	public ControlCommandEnum getControlCommand() {
		return controlCommand;
	}

	public void setControlCommand(ControlCommandEnum controlCommand) {
		this.controlCommand = controlCommand;
	}

	@Override
	public String toString() {
		// here to ignore GC problem due to +
		return fromFloorNumber + "-" + toFloorNumber;
	}
}
