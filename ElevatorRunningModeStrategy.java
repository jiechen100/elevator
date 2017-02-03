package com.jie.elevator;

import java.util.List;

public interface ElevatorRunningModeStrategy {
	public Command nextCommand(List<Command> cmds);
}
