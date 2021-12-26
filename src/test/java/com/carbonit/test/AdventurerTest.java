package com.carbonit.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AdventurerTest {

	Adventurer adventurerUnderTest;
	TreasureMap treasureMapUnderTest;

	@Test
	public void testChangeOrientation() {
		adventurerUnderTest = new Adventurer("Bob", 0, 0, Orientation.S, "AGADA");
		Orientation orientationAfterChange = adventurerUnderTest.changeDirection("D");

		assertThat(orientationAfterChange).isEqualTo(Orientation.O);
	}

	@Test
	public void testMoveForward() {
		adventurerUnderTest = new Adventurer("Bob", 0, 0, Orientation.S, "AGADA");
		int expectedPositionX = 0;
		int expectedPositionY = 1;

		adventurerUnderTest.moveForward();

		assertThat(expectedPositionX).isEqualTo(adventurerUnderTest.getPositionX());
		assertThat(expectedPositionY).isEqualTo(adventurerUnderTest.getPositionY());
	}

	@Test
	public void testExecuteMovements() {
		adventurerUnderTest = new Adventurer("Bob", 0, 0, Orientation.N, "ADADA");
		treasureMapUnderTest = new TreasureMap(3, 3);
		int expectedPositionX = 1;
		int expectedPositionY = 1;
		for (int indexW = 0; indexW < treasureMapUnderTest.getWidth(); indexW++) {
			for (int indexH = 0; indexH < treasureMapUnderTest.getHeight(); indexH++) {
				treasureMapUnderTest.getSquares()[indexW][indexH] = new Square("P", indexW, indexH);
			}
		}

		treasureMapUnderTest.executeSequence(adventurerUnderTest);

		assertThat(expectedPositionX).isEqualTo(adventurerUnderTest.getPositionX());
		assertThat(expectedPositionY).isEqualTo(adventurerUnderTest.getPositionY());
	}

}
