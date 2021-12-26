package com.carbonit.test;

public class Square {

	private String type;
	private int positionX;
	private int positionY;
	private int numberOfTreasures;
	private Adventurer adventurer;

	public Square(String type, int positionX, int positionY, int numberOfTreasures) {
		this.type = type;
		this.positionX = positionX;
		this.positionY = positionY;
		this.numberOfTreasures = numberOfTreasures;
	}

	public Square(String type, int positionX, int positionY) {
		this.type = type;
		this.positionX = positionX;
		this.positionY = positionY;
		this.numberOfTreasures = 0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getNumberOfTreasures() {
		return numberOfTreasures;
	}

	public void setNumberOfTreasures(int numberOfTreasures) {
		this.numberOfTreasures = numberOfTreasures;
	}

	public Adventurer getAdventurer() {
		return adventurer;
	}

	public void setAdventurer(Adventurer adventurer) {
		this.adventurer = adventurer;
	}

	public void decreaseNumberOfTreasures() {
		this.numberOfTreasures--;
	}

}
