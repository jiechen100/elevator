package com.jie.elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements ElevatorBehavior {
	private ElevatorController controller;
	private int currentFloor;
	private PathTracking path;

	public Elevator(ElevatorController controller) {
		this.controller = controller;
		this.path = new PathTracking();
	}

	public class PathTracking {

		private List<Integer> path = new ArrayList<Integer>();
		private StringBuilder sb = new StringBuilder();

		public void add(int floorNbr) {
			path.add(floorNbr);
		}

		public void reset() {
			path.clear();
		}

		public String getPath() {
			int lastFloor = 0;
			int distance = 0;

			sb.setLength(0);
			for (Integer floorNbr : path) {
				sb.append(floorNbr);
				sb.append(" ");

				if (lastFloor > 0) {
					distance += Math.abs(floorNbr - lastFloor);
				}

				lastFloor = floorNbr;
			}

			sb.append("(");
			sb.append(distance);
			sb.append(")");

			return sb.toString();
		}
	}

	public void start(int startFloor) {
		this.currentFloor=startFloor;
		path.add(this.currentFloor);

		Command cmd;
		ControlCommandEnum cc;
		while (true) {
			cmd = controller.nextCommand();
			cc = cmd.getControlCommand();
			if (ControlCommandEnum.STOP == cc) {
				stop();
				break;
			}

			if (ControlCommandEnum.MOVE == cc) {
				move(cmd);
			}
		}
	}

	public void stop() {
		printPath();
		
		currentFloor = 0;
		path.reset();		
	}

	public synchronized void move(Command cmd) {

		if (this.currentFloor != cmd.getFromFloorNumber()) {
			path.add(cmd.getFromFloorNumber());
		}
		path.add(cmd.getToFloorNumber());
		this.currentFloor = cmd.getToFloorNumber();
	}

	public void printPath() {
		System.out.println(path.getPath());
		System.out.println();
	}
}
