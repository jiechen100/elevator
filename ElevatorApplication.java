package com.jie.elevator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElevatorApplication {

	private boolean validateArgs(String[] args) {
		boolean valid = true;

		if (args == null || args.length < 2) {
			valid = false;
		}

		if (valid) {
			if (!ElevatorRunningStrategyEnum.A.description().equals(args[1])
					&& !ElevatorRunningStrategyEnum.B.description().equals(
							args[1])) {
				valid = false;
			}
		}

		if (valid) {
			if (!ElevatorRunningStrategyEnum.A.description().equals(args[1])
					&& !ElevatorRunningStrategyEnum.B.description().equals(
							args[1])) {
				valid = false;
			}
		}

		if (!valid) {
			System.out
					.println("Usage:\tjava ElevatorApplication command-file mode");
			System.out
					.println("\tcommand-file: a file containging a list of elevator requests, e.g. 4-1,3-2,1-5,6-8");
			System.out.println("\tmode: A simple mode or B complicated mode");
			System.out
					.println("\tFor example: java ElevatorApplication c:\\elecmds.txt A");
		}

		return valid;
	}

	private List<String> getRequestLines(String filename) {
		// assume only one line in that file, e.g. 10:4-1,3-2,1-5,6-8

		List<String> lines = new ArrayList<String>();
		String line = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {

			try {

				if (br != null) {
					br.close();
				}

			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
		}

		return lines;
	}

	public static void main(String[] args) {
		ElevatorApplication app = new ElevatorApplication();
		if (!app.validateArgs(args)) {
			System.exit(1);
		}

		List<String> requestLines = app.getRequestLines(args[0]);
		if (requestLines == null || requestLines.size() == 0) {
			System.exit(1);
		}

		ElevatorController controller = new ElevatorController();
		for (String line : requestLines) {
			controller.start(args[1], line);
		}
	}

}
