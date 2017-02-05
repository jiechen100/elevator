import java.util.List;

public class ElevatorRunningStrategyA implements ElevatorRunningModeStrategy {

	public Command nextCommand(List<Command> cmds) {
		if (cmds == null || cmds.size() == 0) {
			return new Command(ControlCommandEnum.STOP);
		}

		Command cmd = cmds.get(0);
		cmds.remove(0);

		return cmd;
	}
}

