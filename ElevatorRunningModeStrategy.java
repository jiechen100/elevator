import java.util.List;

public interface ElevatorRunningModeStrategy {
	public Command nextCommand(List<Command> cmds);
}
