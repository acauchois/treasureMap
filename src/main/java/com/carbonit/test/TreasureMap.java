package com.carbonit.test;

import java.util.ArrayList;

public class TreasureMap {

	private int width;
	private int height;
	private Square[][] squares;
	private ArrayList<Adventurer> adventurers;

	public TreasureMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.squares = new Square[width][height];
	}

	public ArrayList<Adventurer> getAdventurers() {
		return adventurers;
	}

	public void setAdventurers(ArrayList<Adventurer> adventurers) {
		this.adventurers = adventurers;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isPassable(Square square, ArrayList<Adventurer> adventurers) {
		boolean passable = true;

		if (square.getType() == "M") {
			passable = false;
		}

		for (Adventurer adventurer : adventurers) {
			if (adventurer.getPositionX() == square.getPositionX()
					&& adventurer.getPositionY() == square.getPositionY()) {
				passable = false;
			}
		}

		return passable;
	}

	public void gatherTreasure(Square square, Adventurer adventurerUnderTest) {
		square.decreaseNumberOfTreasures();
		adventurerUnderTest.increaseNumberOfTreasures();
	}

	public boolean isMovementPossible(Adventurer adventurer) {
		boolean possible = true;
		if (adventurer.getPresentMovement().equals("A")) {
			if (adventurer.getOrientation().toString().equals("N") && adventurer.getPositionY() == 0) {
				possible = false;
			} else if (adventurer.getOrientation().toString().equals("E")
					&& adventurer.getPositionX() == this.getWidth() - 1) {
				possible = false;
			} else if (adventurer.getOrientation().toString().equals("O") && adventurer.getPositionX() == 0) {
				possible = false;
			} else if (adventurer.getOrientation().toString().equals("S")
					&& adventurer.getPositionX() == this.getHeight() - 1) {
				possible = false;
			}
		}

		return possible;
	}

	public void executeSequence(Adventurer adventurer) {
		for (int index = 0; index < adventurer.getMovements().length(); index++) {
			if (this.isMovementPossible(adventurer)) {
				adventurer.executeMove();
			} else {
				adventurer.nextMove();
			}
		}
	}

}
