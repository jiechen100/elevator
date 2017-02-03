package com.jie.elevator;

import java.util.List;

public class ElevatorRunningStrategyB implements ElevatorRunningModeStrategy {

	public Command nextCommand(List<Command> cmds) {
		if (cmds == null || cmds.size() == 0) {
			return new Command(ControlCommandEnum.STOP);
		}

		Command cmd = cmds.get(0);
		if (cmd.getControlCommand() != ControlCommandEnum.MOVE) {
			cmds.remove(0);
			return cmd;
		}
		
		

		return cmd;
	}
}
