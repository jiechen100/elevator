import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ElevatorController {
	public static final int HIGHEST_FLOOR = 12;
	private int startFloor = 1;
	private List<Command> requests;
	private ElevatorRunningModeStrategy modeStrategy;
	private Elevator elevator;

	public ElevatorController() {
		this.requests = new ArrayList<Command>();
		this.elevator = new Elevator(this);
	}

	private void reset(String mode) {
		requests.clear();
		this.startFloor = 1;
		this.modeStrategy = getModeStrategy(mode);
	}

	public void start(String mode, String requestCommandLine) {
		reset(mode);
		loadCommands(requestCommandLine);
		if (this.requests != null && this.requests.size() > 0) {
			System.out.println("INFO:\tMode=" + mode + "\t\tCommands="
					+ requestCommandLine);
			elevator.start(this.startFloor);
		}
	}

	private void loadCommands(String requestCommandLine) {

		try {
			StringTokenizer stringTokenizer;

			int colIndex = requestCommandLine.indexOf(':');
			if (colIndex > 0) {
				startFloor = Integer.parseInt(requestCommandLine.substring(0,
						colIndex));
				stringTokenizer = new StringTokenizer(
						requestCommandLine.substring(colIndex + 1), ",");
			} else {
				System.out
						.println("ERROR: Command file has no starting floor. Use 1st floor as default starting floor.");
				stringTokenizer = new StringTokenizer(requestCommandLine, ",");
			}

			while (stringTokenizer.hasMoreElements()) {

				String request = stringTokenizer.nextElement().toString();
				if (request == null || request.length() < 3) {
					continue;
				}

				String[] floors = request.split("-");
				if (floors == null || floors.length != 2) {
					continue;
				}

				int from = Integer.parseInt(floors[0]);
				int to = Integer.parseInt(floors[1]);
				if (!isValidFloorNumber(from) || !isValidFloorNumber(to)) {
					System.out
							.println("ERROR: Floor number has to be between 1 and "
									+ HIGHEST_FLOOR + ".");
					continue;
				}

				requests.add(new Command(from, to));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValidFloorNumber(int flNbr) {
		return flNbr >= 1 && flNbr <= HIGHEST_FLOOR;
	}

	private ElevatorRunningModeStrategy getModeStrategy(String mode) {

		ElevatorRunningStrategyEnum modeEnum = ElevatorRunningStrategyEnum
				.get(mode);
		if (ElevatorRunningStrategyEnum.A == modeEnum) {
			return new ElevatorRunningStrategyA();
		}

		if (ElevatorRunningStrategyEnum.B == modeEnum) {
			return new ElevatorRunningStrategyB();
		}

		return new ElevatorRunningStrategyA();
	}

	public Command nextCommand() {
		return modeStrategy.nextCommand(requests);
	}
}
