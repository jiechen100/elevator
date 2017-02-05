public class Command {
	private ControlCommandEnum controlCommand;
	private int fromFloorNumber;
	private int toFloorNumber;
	private Command nextCommand;

	public Command(ControlCommandEnum todo) {
		this.controlCommand = todo;
	}

	public Command(int fromFloorNumber, int toFloorNumber) {
		this.controlCommand = ControlCommandEnum.MOVE;
		this.fromFloorNumber = fromFloorNumber;
		this.toFloorNumber = toFloorNumber;
	}

	public boolean isGoingUp() {
		return fromFloorNumber < toFloorNumber;
	}

	public boolean isGoingDown() {
		return fromFloorNumber > toFloorNumber;
	}

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

	public Command getNextCommand() {
		return nextCommand;
	}

	public void setNextCommand(Command nextCommand) {
		this.nextCommand = nextCommand;
	}

	@Override
	public String toString() {
		// here to ignore GC problem due to +
		return fromFloorNumber + "-" + toFloorNumber;
	}
}
