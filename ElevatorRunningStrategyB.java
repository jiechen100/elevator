import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElevatorRunningStrategyB implements ElevatorRunningModeStrategy {

	public Command nextCommand(List<Command> cmds) {
		if (cmds == null || cmds.size() == 0) {
			return new Command(ControlCommandEnum.STOP);
		}

		Command cmd0 = cmds.get(0);
		if (cmd0.getControlCommand() != ControlCommandEnum.MOVE) {
			cmds.remove(0);
			return cmd0;
		}

		return nextCommandByPiggyback(cmds);
	}

	private Command nextCommandByPiggyback(List<Command> cmds) {

		int indexOfLastPiggback = -1;
		Command cmd = null;

		List<Integer> floorList = new ArrayList<Integer>();
		Command cmd0 = cmds.get(0);
		for (int i = 0; i < cmds.size(); i++) {
			cmd = cmds.get(i);
			if (isSameDirection(cmd0, cmd)) {
				// if (currentFloor != cmd.getFromFloorNumber()) {
				floorList.add(cmd.getFromFloorNumber());
				// }
				//
				// if (currentFloor != cmd.getToFloorNumber()) {
				floorList.add(cmd.getToFloorNumber());
				// }

				indexOfLastPiggback = i;
			} else {
				break;
			}
		}

		if (indexOfLastPiggback >= 0) {
			for (int i = indexOfLastPiggback; i >= 0; i--) {
				cmds.remove(i);
			}

			List<Integer> distinctList = floorList.stream().distinct()
					.collect(Collectors.toList());
			if (cmd0.isGoingUp()) {
				Collections.sort(distinctList, (x, y) -> x.compareTo(y));
			} else {
				Collections.sort(distinctList, (x, y) -> y.compareTo(x));
			}

			int listSize = distinctList.size();
			cmd0 = null;
			for (int i = 0; i < listSize / 2; i++) {
				Command newCmd = new Command(distinctList.get(2 * i),
						distinctList.get(2 * i + 1));
				if (cmd0 == null) {
					cmd0 = newCmd;
					cmd = newCmd;
				} else {
					cmd.setNextCommand(newCmd);
					cmd = newCmd;
				}
			}

			if (listSize % 2 == 1) {
				cmd.setNextCommand(new Command(cmd.getToFloorNumber(),
						distinctList.get(listSize - 1)));
			}
		}

		return cmd0;
	}

	private boolean isSameDirection(Command cmd0, Command cmd) {
		return (cmd0.isGoingUp() && cmd.isGoingUp())
				|| (cmd0.isGoingDown() && cmd.isGoingDown());
	}
}
