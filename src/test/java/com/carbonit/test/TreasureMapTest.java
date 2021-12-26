package com.carbonit.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreasureMapTest {

	TreasureMap treasureMapUnderTest;
	ArrayList<Adventurer> adventurers;
	Adventurer adventurerUnderTest;
	Square squareUnderTest;
	Square[][] squaresUnderTest;

	@BeforeEach
	public void initTreasureMap() {
		treasureMapUnderTest = new TreasureMap(2, 2);
		squaresUnderTest = new Square[2][2];
		adventurers = new ArrayList<Adventurer>();
	}

	@AfterEach
	public void undefTreasureMap() {
		treasureMapUnderTest = null;
	}

	@Test
	public void testIsPassableMountain() {
		squaresUnderTest[0][0] = new Square("P", 0, 0);
		squaresUnderTest[0][1] = new Square("M", 0, 1);

		boolean isPassable = treasureMapUnderTest.isPassable(squaresUnderTest[0][1], adventurers);

		assertThat(isPassable).isFalse();
	}

	@Test
	public void testIsPassableAnotherAdventurer() {
		squaresUnderTest[0][0] = new Square("P", 0, 0);
		squaresUnderTest[0][1] = new Square("M", 0, 1);
		squaresUnderTest[1][0] = new Square("P", 1, 0);
		adventurerUnderTest = new Adventurer("Tom", 1, 0, Orientation.N, "AGAGAGAGAAAA");
		adventurers.add(adventurerUnderTest);

		boolean isPassable = treasureMapUnderTest.isPassable(squaresUnderTest[1][0], adventurers);

		assertThat(isPassable).isFalse();
	}

	@Test
	public void testGatherTreasure() {
		squaresUnderTest[0][0] = new Square("P", 0, 0);
		squaresUnderTest[0][1] = new Square("P", 0, 1, 1);
		adventurerUnderTest = new Adventurer("Rémy", 0, 0, Orientation.E, "AAAAAAADA");
		adventurers.add(adventurerUnderTest);
		adventurerUnderTest.moveForward();
		int numberOfTreasuresSquareBefore = squaresUnderTest[0][1].getNumberOfTreasures();
		int numberOfTreasuresAdventurerBefore = adventurerUnderTest.getNumberOfTreasures();

		treasureMapUnderTest.gatherTreasure(squaresUnderTest[0][1], adventurerUnderTest);

		assertThat(squaresUnderTest[0][1].getNumberOfTreasures()).isEqualTo(numberOfTreasuresSquareBefore - 1);
		assertThat(adventurerUnderTest.getNumberOfTreasures()).isEqualTo(numberOfTreasuresAdventurerBefore + 1);
	}

}
