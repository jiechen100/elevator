package com.jie.elevator;

public interface ElevatorBehavior {
	public void start(int startFloor);

	public void move(Command cmd);

	public void printPath();
}
