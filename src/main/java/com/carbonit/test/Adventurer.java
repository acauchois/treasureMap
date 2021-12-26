package com.carbonit.test;

public class Adventurer {

	private String name;
	private int positionX;
	private int positionY;
	private Orientation orientation;
	private String movements;
	private int numberOfTreasures;
	private String presentMovement;
	private int indexMovement;

	public Adventurer(String name, int positionX, int positionY, Orientation orientation, String movements) {
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.orientation = orientation;
		this.movements = movements;
		this.indexMovement = 0;
		this.presentMovement = movements.substring(this.indexMovement, this.indexMovement + 1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public String getMovements() {
		return movements;
	}

	public void setMovements(String movements) {
		this.movements = movements;
	}

	public int getNumberOfTreasures() {
		return numberOfTreasures;
	}

	public void setNumberOfTreasures(int numberOfTreasures) {
		this.numberOfTreasures = numberOfTreasures;
	}

	public String getPresentMovement() {
		return presentMovement;
	}

	public void setPresentMovement(String presentMovement) {
		this.presentMovement = presentMovement;
	}

	public void increaseNumberOfTreasures() {
		this.numberOfTreasures++;
	}

	public int getIndexMovement() {
		return indexMovement;
	}

	public Orientation changeDirection(String direction) {
		if (direction.equals("G")) {
			this.orientation = this.orientation.getLeft();
		} else if (direction.equals("D")) {
			this.orientation = this.orientation.getRight();
		}
		return this.orientation;
	}

	public void moveForward() {
		if (this.orientation.toString() == "N") {
			this.positionY--;
		} else if (this.orientation.toString() == "E") {
			this.positionX++;
		} else if (this.orientation.toString() == "S") {
			this.positionY++;
		} else {
			this.positionX--;
		}
	}

	public Square nextSquare() {
		int positionX = 0;
		int positionY = 0;

		if (this.orientation.toString() == "N") {
			positionY = this.positionY - 1;
		} else if (this.orientation.toString() == "E") {
			positionX = this.positionX + 1;
		} else if (this.orientation.toString() == "S") {
			positionY = this.positionY + 1;
		} else {
			positionX = this.positionX - 1;
		}

		Square square = new Square("P", positionX, positionY);

		return square;
	}

	public void nextMove() {
		if (this.indexMovement < this.movements.length() - 1) {
			this.indexMovement++;
			this.presentMovement = this.movements.substring(this.indexMovement, this.indexMovement + 1);
		} else if (this.indexMovement == this.movements.length() - 1) {
			this.indexMovement++;
			this.presentMovement = this.movements.substring(this.indexMovement);
		}
	}

	public void executeMove() {
		switch (this.presentMovement) {
		case "A":
			this.moveForward();
			this.nextMove();
			break;

		case "G":
		case "D":
			this.changeDirection(this.presentMovement);
			this.nextMove();
			break;
		}
	}

}
