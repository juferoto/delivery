package com.delivery.model;

public class Position
{
	private int x;
	private int y;
	private char orientation;

	public Position() {
		super();
		//Default position for a drone
		this.x = 0;
		this.y = 0;
		this.orientation = 'N';
	}

	public Position(int x, int y, char orientation) {
		super();
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
		this.orientation = position.getOrientation();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}

}
